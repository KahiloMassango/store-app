package com.example.store.core.network

import com.example.store.core.network.common.extractErrorMessage
import com.example.store.core.network.datasources.StoreNetworkDatasource
import com.example.store.core.network.model.StoreDtoRes
import com.example.store.core.network.retrofit.AuthenticatedApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class StoreNetworkDatasourceImpl(
    private val apiService: AuthenticatedApiService
): StoreNetworkDatasource {
    override suspend fun getStoreDetail(id: String): Result<StoreDtoRes> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getStoreDetail(id)
                Result.success(response.data)
            } catch (e: HttpException) {
                val message = extractErrorMessage(e)
                Result.failure(Exception(message))
            } catch (e: IOException) {
                Result.failure(Exception("Verifique sua conexão com a internet"))
            }
        }
    }
}
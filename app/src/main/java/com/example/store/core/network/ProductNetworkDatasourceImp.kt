package com.example.store.core.network

import com.example.store.core.network.common.extractErrorMessage
import com.example.store.core.network.datasources.ProductNetworkDatasource
import com.example.store.core.network.model.product.ProductDtoRes
import com.example.store.core.network.model.product.ProductDetailDtoRes
import com.example.store.core.network.retrofit.PublicApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class ProductNetworkDatasourceImp(
    private val apiService: PublicApiService
): ProductNetworkDatasource {

    override suspend fun getProductsByGenderAndCategory(
        gender: String?,
        category: String?
    ): Result<List<ProductDtoRes>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getProducts(gender, category)
                Result.success(response.data)
            } catch(e: HttpException){
                val message = extractErrorMessage(e)
                Result.failure(Exception(message))
            } catch (e: IOException) {
                Result.failure(IOException("Verifique sua conexão com a internet"))
            }
        }
    }

    override suspend fun getProductById(id: String): Result<ProductDetailDtoRes> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getProductById(id)
                Result.success(response.data)
            } catch(e: HttpException){
                val message = extractErrorMessage(e)
                Result.failure(Exception(message))
            } catch (e: IOException) {
                Result.failure(IOException("Verifique sua conexão com a internet"))
            }
        }
    }

    override suspend fun searchProducts(query: String): Result<List<ProductDtoRes>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.searchProducts(query)
                Result.success(response.data)
            } catch(e: HttpException){
                val message = extractErrorMessage(e)
                Result.failure(Exception(message))
            } catch (e: IOException) {
                Result.failure(IOException("Verifique sua conexão com a internet"))
            }
        }
    }
}
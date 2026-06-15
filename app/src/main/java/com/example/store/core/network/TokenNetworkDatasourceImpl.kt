package com.example.store.core.network

import com.example.store.core.network.datasources.TokenNetworkDatasource
import com.example.store.core.network.model.authentication.RefreshTokenDtoReq
import com.example.store.core.network.retrofit.PublicApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TokenNetworkDatasourceImpl(
    private val publicApiService: PublicApiService
): TokenNetworkDatasource {

    override suspend fun getRefreshToken(refreshToken: String): Result<String> {
        return withContext(Dispatchers.IO) {
            try {
                val response = publicApiService.refreshToken(RefreshTokenDtoReq(refreshToken))
                Result.success(response.data.accessToken)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

}
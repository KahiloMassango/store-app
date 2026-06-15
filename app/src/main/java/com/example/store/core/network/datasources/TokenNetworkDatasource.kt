package com.example.store.core.network.datasources

interface TokenNetworkDatasource {
    suspend fun getRefreshToken(refreshToken: String): Result<String>
}
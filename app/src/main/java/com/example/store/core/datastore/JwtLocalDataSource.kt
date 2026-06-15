package com.example.store.core.datastore

import kotlinx.coroutines.flow.Flow

interface JwtLocalDataSource {
    val isLoggedIn: Flow<Boolean>
    suspend fun saveAccessToken(token: String)
    suspend fun saveRefreshToken(token: String)
    suspend fun getAccessToken(): String?
    suspend fun getRefreshToken(): String?
    suspend fun clearAllTokens()
}
package com.example.store.core.testing.fake_datasources

import com.example.store.core.datastore.JwtLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeJwtLocalDataSource: JwtLocalDataSource {

    private var accessToken: String? = null
    private var refreshToken: String? = null

    private val _isLoggedIn = MutableStateFlow(false)
    override val isLoggedIn: Flow<Boolean> = _isLoggedIn

    override suspend fun saveAccessToken(token: String) {
        accessToken = token
        _isLoggedIn.value = true
    }

    override suspend fun saveRefreshToken(token: String) {
        refreshToken = token
    }

    override suspend fun getAccessToken(): String? {
        return accessToken
    }

    override suspend fun getRefreshToken(): String? {
        return refreshToken
    }

    override suspend fun clearAllTokens() {
        accessToken = null
        refreshToken = null
        _isLoggedIn.value = false
    }
}
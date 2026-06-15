package com.example.store.core.network.interceptors

import com.example.store.core.datastore.JwtLocalDataSource
import com.example.store.core.network.datasources.TokenNetworkDatasource
import com.example.store.core.network.datasources.UserNetworkDatasource
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class AuthenticatorInterceptor @Inject constructor(
    private val tokenDataSource: TokenNetworkDatasource,
    private val tokenManager: JwtLocalDataSource,
) : Authenticator {

    companion object {
        const val HEADER_AUTHORIZATION = "Authorization"
        const val TOKEN_TYPE = "Bearer"
    }

    override fun authenticate(route: Route?, response: Response): Request? {
        val currentToken = runBlocking { tokenManager.getAccessToken() }

        synchronized(this) {
            val updatedToken = runBlocking { tokenManager.getAccessToken() }
            val refreshToken = runBlocking { tokenManager.getRefreshToken() } ?: ""

            val token = if (currentToken != updatedToken) updatedToken else {
                runBlocking { tokenDataSource.getRefreshToken(refreshToken) }
                    .getOrNull()?.let { accessToken ->
                        runBlocking {
                            tokenManager.saveAccessToken(accessToken)
                        }
                        accessToken
                    }
            }

            if (token == null) {
                runBlocking {
                    tokenManager.clearAllTokens()
                }
            }

            return token?.let {
                response.request.newBuilder()
                    .header(HEADER_AUTHORIZATION, "$TOKEN_TYPE $token")
                    .build()
            }
        }
    }
}
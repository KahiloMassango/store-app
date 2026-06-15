package com.example.store.core.network

import com.example.store.core.network.common.extractErrorMessage
import com.example.store.core.network.datasources.UserNetworkDatasource
import com.example.store.core.network.model.authentication.AuthenticationDtoRes
import com.example.store.core.network.model.authentication.LoginDtoReq
import com.example.store.core.network.model.authentication.RefreshTokenDtoReq
import com.example.store.core.network.model.user.UserDtoReq
import com.example.store.core.network.model.user.UserDtoRes
import com.example.store.core.network.model.user.UserUpdateDtoReq
import com.example.store.core.network.retrofit.AuthenticatedApiService
import com.example.store.core.network.retrofit.PublicApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class UserNetworkDatasourceImpl(
    private val publicApiService: PublicApiService,
    private val authenticatedApiService: AuthenticatedApiService
) : UserNetworkDatasource {

    override suspend fun login(request: LoginDtoReq): Result<AuthenticationDtoRes> {
        return withContext(Dispatchers.IO) {
            try {
                val response = publicApiService.login(request)
                Result.success(response.data)
            } catch (e: HttpException) {
                val message = extractErrorMessage(e)
                Result.failure(Exception(message))
            } catch (e: IOException) {
                Result.failure(Exception("Verifique sua conexão com a internet"))
            }
        }
    }

    override suspend fun createAccount(request: UserDtoReq): Result<Unit> {
        return withContext(Dispatchers.IO) {
            try {
                publicApiService.createAccount(request)
                Result.success(Unit)
            } catch (e: HttpException) {
                val message = extractErrorMessage(e)
                Result.failure(Exception(message))
            } catch (e: IOException) {
                Result.failure(Exception("Verifique sua conexão com a internet"))
            }
        }
    }

    override suspend fun logout(): Result<Unit> {
        return withContext(Dispatchers.IO) {
            try {
                authenticatedApiService.logout()
                Result.success(Unit)
            } catch (e: HttpException) {
                val message = extractErrorMessage(e)
                Result.failure(Exception(message))
            } catch (e: IOException) {
                Result.failure(Exception("Verifique sua conexão com a internet"))
            }
        }
    }


    override suspend fun getAccountDetails(): Result<UserDtoRes> {
        return withContext(Dispatchers.IO) {
            try {
                val response = authenticatedApiService.getAccountDetails()
                Result.success(response.data)
            } catch (e: HttpException) {
                val message = extractErrorMessage(e)
                Result.failure(Exception(message))
            } catch (e: IOException) {
                Result.failure(Exception("Verifique sua conexão com a internet"))
            }
        }
    }


    override suspend fun updateAccount(request: UserUpdateDtoReq): Result<UserDtoRes> {
        return withContext(Dispatchers.IO) {
            try {
                val response = authenticatedApiService.updateAccount(request)
                Result.success(response.data)
            } catch (e: HttpException) {
                val message = extractErrorMessage(e)
                Result.failure(Exception(message))
            } catch (e: IOException) {
                Result.failure(Exception("Verifique sua conexão com a internet"))
            }
        }
    }

    override suspend fun updatePassword(newPassword: String): Result<Unit> {
        return withContext(Dispatchers.IO) {
            try {
                val response = authenticatedApiService.updatePassword(newPassword)
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
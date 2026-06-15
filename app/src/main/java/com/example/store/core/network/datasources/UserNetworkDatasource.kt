package com.example.store.core.network.datasources

import com.example.store.core.network.model.authentication.AuthenticationDtoRes
import com.example.store.core.network.model.authentication.LoginDtoReq
import com.example.store.core.network.model.user.UserDtoReq
import com.example.store.core.network.model.user.UserDtoRes
import com.example.store.core.network.model.user.UserUpdateDtoReq

interface UserNetworkDatasource {
    suspend fun login(request: LoginDtoReq): Result<AuthenticationDtoRes>

    suspend fun createAccount(request: UserDtoReq): Result<Unit>

    suspend fun logout(): Result<Unit>

    suspend fun getAccountDetails(): Result<UserDtoRes>

    suspend fun updateAccount(request: UserUpdateDtoReq): Result<UserDtoRes>

    suspend fun updatePassword(newPassword: String): Result<Unit>

}
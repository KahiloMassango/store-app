package com.example.store.core.testing.fake_datasources

import com.example.store.core.network.datasources.UserNetworkDatasource
import com.example.store.core.network.model.authentication.AuthenticationDtoRes
import com.example.store.core.network.model.authentication.LoginDtoReq
import com.example.store.core.network.model.user.UserDtoReq
import com.example.store.core.network.model.user.UserDtoRes
import com.example.store.core.network.model.user.UserUpdateDtoReq
import com.example.store.core.testing.fake_data.fakeAuthenticationDtoRes
import kotlinx.coroutines.flow.MutableStateFlow

class FakeUserNetworkDataSource: UserNetworkDatasource {

    private var fakeUser: UserDtoRes? = null
    private var isLoggedIn = MutableStateFlow(false)

    override suspend fun login(request: LoginDtoReq): Result<AuthenticationDtoRes> {
        return if (fakeUser != null && request.identifier == fakeUser?.email) {
            isLoggedIn.value = true
            Result.success(fakeAuthenticationDtoRes)
        } else {
            Result.failure(Exception("Invalid credentials"))
        }
    }

    override suspend fun createAccount(request: UserDtoReq): Result<Unit> {
        fakeUser = UserDtoRes(request.username, request.email, request.phoneNumber)
        return Result.success(Unit)
    }

    override suspend fun logout(): Result<Unit> {
        isLoggedIn.value = false
        return Result.success(Unit)
    }

    override suspend fun getAccountDetails(): Result<UserDtoRes> {
        if(!isLoggedIn.value){
            return Result.failure(Exception("User not logged in"))
        }
        return Result.success(fakeUser!!)
    }

    override suspend fun updateAccount(request: UserUpdateDtoReq): Result<UserDtoRes> {
        if(!isLoggedIn.value){
            return Result.failure(Exception("User not logged in"))
        }
        fakeUser = UserDtoRes(request.username, request.email, request.phoneNumber)
        return Result.success(fakeUser!!)
    }

    override suspend fun updatePassword(newPassword: String): Result<Unit> {
        if(!isLoggedIn.value){
            return Result.failure(Exception("User not logged in"))
        }
        return Result.success(Unit)
    }
}
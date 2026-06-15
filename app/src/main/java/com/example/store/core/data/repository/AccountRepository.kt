package com.example.store.core.data.repository

import com.example.store.core.model.user.User
import kotlinx.coroutines.flow.Flow

interface AccountRepository {
    suspend fun login(email: String, password: String): Result<Unit>
    suspend fun logout(): Result<Unit>
    suspend fun updateUser(username: String, email: String, phoneNumber: String): Result<User>

    suspend fun updateRemoteUserPassword(password: String): Result<Unit>
    suspend fun getLocalUserDetails(): User
    suspend fun fetchAccountDetails(): Result<User>

    suspend fun saveLocalUserDetails(user: User)
    fun isUserLoggedIn(): Flow<Boolean>

    suspend fun createAccount(
        username: String,
        email: String,
        password: String,
        phoneNumber: String,
    ): Result<Unit>



}
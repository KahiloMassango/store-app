package com.example.store.core.testing.fake_repositories

import com.example.store.core.data.repository.AccountRepository
import com.example.store.core.model.user.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf

class FakeAccountRepository : AccountRepository {

    private var shouldFail = false
    private var loggedIn = false
    private var localUser: User? = null

    fun setShouldFail(value: Boolean) {
        shouldFail = value
    }

    fun setUserLoggedIn(value: Boolean) {
        loggedIn = value
    }

    override suspend fun login(email: String, password: String): Result<Unit> {
        return if (shouldFail) {
            Result.failure(Exception("Network error"))
        } else {
            loggedIn = true
            localUser = User("John Doe", "john.doe@example.com", "1234567890")
            Result.success(Unit)
        }
    }

    override suspend fun logout(): Result<Unit> {
        return if (shouldFail) {
            Result.failure(Exception("Network error"))
        } else {
            loggedIn = false
            Result.success(Unit)
        }
    }

    override suspend fun updateUser(username: String, email: String, phoneNumber: String): Result<User> {
        return if (shouldFail) {
            Result.failure(Exception("Network error"))
        } else {
            val updatedUser = User(username, email, phoneNumber)
            localUser = updatedUser
            Result.success(updatedUser)
        }
    }

    override suspend fun updateRemoteUserPassword(password: String): Result<Unit> {
        return if (shouldFail) {
            Result.failure(Exception("Network error"))
        } else {
            Result.success(Unit)
        }
    }

    override suspend fun getLocalUserDetails(): User {
        return localUser ?: throw Exception("No local user found")
    }

    override suspend fun fetchAccountDetails(): Result<User> {
        return if (shouldFail) {
            Result.failure(Exception("Network error"))
        } else {
            localUser?.let { Result.success(it) } ?: Result.failure(Exception("User not found"))
        }
    }

    override suspend fun saveLocalUserDetails(user: User) {
        localUser = user
    }

    override fun isUserLoggedIn(): Flow<Boolean> = flowOf(loggedIn)

    override suspend fun createAccount(
        username: String,
        email: String,
        password: String,
        phoneNumber: String
    ): Result<Unit> {
        return if (shouldFail) {
            Result.failure(Exception("Network error"))
        } else {
            localUser = User(username, email, phoneNumber)
            loggedIn = true
            Result.success(Unit)
        }
    }
}

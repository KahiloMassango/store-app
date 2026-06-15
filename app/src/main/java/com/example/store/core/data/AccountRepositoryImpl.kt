package com.example.store.core.data

import com.example.store.core.data.repository.AccountRepository
import com.example.store.core.datastore.JwtLocalDataSource
import com.example.store.core.datastore.UserLocalDataSource
import com.example.store.core.model.user.User
import com.example.store.core.network.datasources.UserNetworkDatasource
import com.example.store.core.network.model.authentication.LoginDtoReq
import com.example.store.core.network.model.user.UserDtoReq
import com.example.store.core.network.model.user.UserUpdateDtoReq
import com.example.store.core.network.model.user.asExternalModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class AccountRepositoryImpl(
    private val userNetworkDatasource: UserNetworkDatasource,
    private val userLocalDataSource: UserLocalDataSource,
    private val tokenLocalDataSource: JwtLocalDataSource
) : AccountRepository {

    override fun isUserLoggedIn(): Flow<Boolean> {
        return tokenLocalDataSource.isLoggedIn
    }

    override suspend fun login(email: String, password: String): Result<Unit> {
        return userNetworkDatasource.login(LoginDtoReq(email, password)).mapCatching {
            tokenLocalDataSource.saveAccessToken(it.accessToken)
            tokenLocalDataSource.saveRefreshToken(it.refreshToken)
            val userResult = userNetworkDatasource.getAccountDetails()
            if (userResult.isSuccess) {
                val user = userResult.getOrThrow().asExternalModel()
                userLocalDataSource.saveUserDetails(user.username, user.email, user.phoneNumber)
            }
        }
    }

    override suspend fun logout(): Result<Unit> {
        return userNetworkDatasource.logout()
            .onSuccess {
                tokenLocalDataSource.clearAllTokens()
                userLocalDataSource.clearUserDetails()
            }
    }

    override suspend fun createAccount(
        username: String,
        email: String,
        password: String,
        phoneNumber: String
    ): Result<Unit> {
        return userNetworkDatasource.createAccount(
            UserDtoReq(
                username,
                email,
                phoneNumber,
                password
            )
        )
    }

    override suspend fun updateUser(
        username: String,
        email: String,
        phoneNumber: String
    ): Result<User> {
        return userNetworkDatasource.updateAccount(UserUpdateDtoReq(username, email, phoneNumber))
            .mapCatching { res ->
                res.asExternalModel().also {
                    userLocalDataSource.saveUserDetails(
                        it.username,
                        it.email,
                        it.phoneNumber
                    )
                }
            }
    }

    override suspend fun updateRemoteUserPassword(password: String): Result<Unit> {
        return userNetworkDatasource.updatePassword(password)
    }

    override suspend fun getLocalUserDetails(): User {
        return User(
            username = userLocalDataSource.getUsername() ?: "",
            email = userLocalDataSource.getEmail() ?: "",
            phoneNumber = userLocalDataSource.getPhoneNumber() ?: ""
        )
    }

    override suspend fun fetchAccountDetails(): Result<User> {
        return userNetworkDatasource.getAccountDetails().mapCatching {
            it.asExternalModel()
        }
    }

    override suspend fun saveLocalUserDetails(user: User) {
        userLocalDataSource.saveUserDetails(
            name = user.username,
            email = user.email,
            phoneNumber = user.phoneNumber
        )

    }
}
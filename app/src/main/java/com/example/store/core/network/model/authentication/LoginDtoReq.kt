package com.example.store.core.network.model.authentication

data class LoginDtoReq(
    val identifier: String,
    val password: String,
)

data class RefreshTokenDtoReq(
    val refreshToken: String
)

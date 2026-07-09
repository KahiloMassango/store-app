package com.example.store.core.network.model.authentication

data class LoginDtoReq(
    val identifier: String,
    val password: String,
    val deviceToken: String? = null,
    val deviceType: String? = null
)

data class RefreshTokenDtoReq(
    val refreshToken: String
)

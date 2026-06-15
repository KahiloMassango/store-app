package com.example.store.core.network.model.authentication

data class AuthenticationDtoRes(
    val accessToken: String,
    val refreshToken: String
)
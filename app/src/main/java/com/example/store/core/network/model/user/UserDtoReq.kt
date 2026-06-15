package com.example.store.core.network.model.user

data class UserDtoReq(
    val username: String,
    val email: String,
    val phoneNumber: String,
    val password: String
)

data class UserUpdateDtoReq(
    val username: String,
    val email: String,
    val phoneNumber: String,
)
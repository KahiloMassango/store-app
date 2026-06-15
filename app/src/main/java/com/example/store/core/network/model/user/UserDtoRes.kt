package com.example.store.core.network.model.user

import com.example.store.core.model.user.User

data class UserDtoRes(
    val username: String,
    val email: String,
    val phoneNumber: String,
)

fun UserDtoRes.asExternalModel() = User(
    username = username,
    email = email,
    phoneNumber = phoneNumber
)

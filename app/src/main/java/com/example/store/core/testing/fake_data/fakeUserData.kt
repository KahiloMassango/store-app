package com.example.store.core.testing.fake_data

import com.example.store.core.model.user.User
import com.example.store.core.network.model.authentication.AuthenticationDtoRes
import com.example.store.core.network.model.user.UserDtoReq
import com.example.store.core.network.model.user.UserDtoRes

val fakeUser1 = User(
    username = "John Doe",
    email = "johndoe@example.com",
    phoneNumber = "9999999"
)

val fakeAuthenticationDtoRes = AuthenticationDtoRes("fakeAccessToken", "fakeRefreshToken")
val fakeUserDtoReq1 = UserDtoReq(
    username = fakeUser1.username,
    email = fakeUser1.email,
    phoneNumber = fakeUser1.phoneNumber,
    password = fakeUser1.username
)

val fakeUserDtoRes1 = UserDtoRes(
    username = fakeUser1.username,
    email = fakeUser1.email,
    phoneNumber = fakeUser1.phoneNumber
)
package com.example.store.core.testing.fake_datasources

import com.example.store.core.datastore.UserLocalDataSource

class FakeUserLocalDataSource: UserLocalDataSource {

    private var username: String? = null
    private var userEmail: String? = null
    private var userPhoneNumber: String? = null

    override suspend fun saveUserDetails(name: String, email: String, phoneNumber: String) {
        username = name
        userEmail = email
        userPhoneNumber = phoneNumber
    }

    override suspend fun clearUserDetails() {
        userEmail = null
        username = null
        userPhoneNumber = null
    }

    override suspend fun getUsername(): String? {
        return username
    }

    override suspend fun getEmail(): String? {
        return userEmail
    }

    override suspend fun getPhoneNumber(): String? {
        return userPhoneNumber
    }
}
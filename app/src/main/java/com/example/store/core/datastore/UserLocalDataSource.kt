package com.example.store.core.datastore

interface UserLocalDataSource {


    suspend fun saveUserDetails(name: String, email: String, phoneNumber: String)
    suspend fun clearUserDetails()

    suspend fun getUsername(): String?
    suspend fun getEmail(): String?
    suspend fun getPhoneNumber(): String?

}
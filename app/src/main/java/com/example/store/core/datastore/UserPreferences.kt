package com.example.store.core.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class UserPreferences(
    private val dataStore: DataStore<Preferences>
) : UserLocalDataSource {

    private val USER_NAME = stringPreferencesKey("user_name")
    private val USER_EMAIL = stringPreferencesKey("user_email")
    private val USER_PHONE_NUMBER = stringPreferencesKey("user_phone_number")
    

    override suspend fun saveUserDetails(name: String, email: String, phoneNumber: String) {
        dataStore.edit { preferences ->
            preferences[USER_NAME] = name
            preferences[USER_EMAIL] = email
            preferences[USER_PHONE_NUMBER] = phoneNumber
        }
    }

    override suspend fun clearUserDetails() {
        withContext(Dispatchers.IO) {
            dataStore.edit { preferences ->
                preferences.remove(USER_NAME)
                preferences.remove(USER_EMAIL)
                preferences.remove(USER_PHONE_NUMBER)
            }
        }
    }
    
    override suspend fun getUsername(): String? {
        return withContext(Dispatchers.IO) {
            dataStore.data.map { preferences -> preferences[USER_NAME] }.first()
        }
    }

    override suspend fun getEmail(): String? {
        return withContext(Dispatchers.IO) {
            dataStore.data.map { preferences -> preferences[USER_EMAIL] }.first()
        }
    }

    override suspend fun getPhoneNumber(): String? {
        return withContext(Dispatchers.IO) {
            dataStore.data.map { preferences -> preferences[USER_PHONE_NUMBER] }.first()
        }
    }
}
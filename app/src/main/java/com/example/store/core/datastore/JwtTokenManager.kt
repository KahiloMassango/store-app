package com.example.store.core.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class JwtTokenManager(
    private val dataStore: DataStore<Preferences>
) : JwtLocalDataSource {


    companion object {
        private val ACCESS_TOKEN = stringPreferencesKey("access_token")
        private val REFRESH_TOKEN = stringPreferencesKey("refresh_token")
    }

    override val isLoggedIn: Flow<Boolean>
        get() = dataStore.data.map { it[ACCESS_TOKEN] != null }

    override suspend fun saveAccessToken(token: String) {
        withContext(Dispatchers.IO) {
            dataStore.edit { preferences ->
                preferences[ACCESS_TOKEN] = token
            }
        }
    }

    override suspend fun saveRefreshToken(token: String) {
        withContext(Dispatchers.IO) {
            dataStore.edit { preferences ->
                preferences[REFRESH_TOKEN] = token
            }
        }
    }

    override suspend fun getAccessToken(): String? {
        return withContext(Dispatchers.IO) {
            dataStore.data.map { preferences -> preferences[ACCESS_TOKEN] }.first()
        }
    }

    override suspend fun getRefreshToken(): String? {
        return withContext(Dispatchers.IO) {
            dataStore.data.map { preferences -> preferences[REFRESH_TOKEN] }.first()
        }
    }

    override suspend fun clearAllTokens() {
        withContext(Dispatchers.IO) {
            dataStore.edit { preferences ->
                preferences.remove(ACCESS_TOKEN)
                preferences.remove(REFRESH_TOKEN)
            }
        }
    }
}
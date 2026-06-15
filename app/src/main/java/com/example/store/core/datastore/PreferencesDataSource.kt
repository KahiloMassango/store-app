package com.example.store.core.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import kotlinx.coroutines.flow.first
import javax.inject.Inject


class AppPreferencesDataSourceImpl @Inject constructor(
    private val datastore: DataStore<Preferences>
): AppPreferencesDataSource {

    companion object {
        private val LAST_SYNC = longPreferencesKey("last_sync")
    }


    override suspend fun getLastUpdated(): Long? {
        return datastore.data.first()[LAST_SYNC]
    }

    override suspend fun setLastUpdated(timestamp: Long) {
        datastore.edit { settings ->
            settings[LAST_SYNC] = timestamp
        }
    }

}
package com.example.store.core.datastore

import androidx.datastore.preferences.protobuf.Timestamp

interface AppPreferencesDataSource {
    suspend fun getLastUpdated(): Long?

    suspend fun setLastUpdated(timestamp: Long)

}
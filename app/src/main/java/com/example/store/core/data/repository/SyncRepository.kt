package com.example.store.core.data.repository

import com.example.store.core.network.model.sync.SyncMetadataDtoRes


interface SyncRepository {
    suspend fun sync(): Result<Unit>
}
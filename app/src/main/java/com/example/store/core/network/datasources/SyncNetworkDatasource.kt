package com.example.store.core.network.datasources

import com.example.store.core.network.model.sync.SyncMetadataDtoRes


interface SyncNetworkDatasource {
    suspend fun getLastUpdate(): Result<Long>
    suspend fun sync(): Result<SyncMetadataDtoRes>

}
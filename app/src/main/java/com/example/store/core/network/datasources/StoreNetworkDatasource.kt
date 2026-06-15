package com.example.store.core.network.datasources

import com.example.store.core.network.model.StoreDtoRes

interface StoreNetworkDatasource {
    suspend fun getStoreDetail(id: String): Result<StoreDtoRes>
}
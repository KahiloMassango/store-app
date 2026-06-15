package com.example.store.core.data

import com.example.store.core.data.repository.StoreRepository
import com.example.store.core.model.store.Store
import com.example.store.core.network.datasources.StoreNetworkDatasource
import com.example.store.core.network.model.asExternalModel

class StoreRepositoryImpl(
    private val storeNetworkDatasource: StoreNetworkDatasource,
): StoreRepository {

    override suspend fun getStoreDetailById(id: String): Result<Store> {
        return storeNetworkDatasource.getStoreDetail(id).mapCatching { it.asExternalModel() }
    }

}
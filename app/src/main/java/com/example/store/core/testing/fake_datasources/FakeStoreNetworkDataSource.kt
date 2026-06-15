package com.example.store.core.testing.fake_datasources

import com.example.store.core.network.datasources.StoreNetworkDatasource
import com.example.store.core.network.model.StoreDtoRes
import com.example.store.core.testing.fake_data.fakeStoreDtoRes1

class FakeStoreNetworkDataSource: StoreNetworkDatasource {

    private var shouldFail = false

    fun setShouldFail(value: Boolean) {
        shouldFail = value
    }

    override suspend fun getStoreDetail(id: String): Result<StoreDtoRes> {
        return if(shouldFail) {
            Result.failure(Exception("Network error"))
        } else {
            if (id == "1") {
                Result.success(fakeStoreDtoRes1)
            } else {
                Result.failure(Exception("Store not found"))
            }
        }
    }

}
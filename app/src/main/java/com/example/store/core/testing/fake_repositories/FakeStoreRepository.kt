package com.example.store.core.testing.fake_repositories

import com.example.store.core.data.repository.StoreRepository
import com.example.store.core.model.store.Store
import com.example.store.core.testing.fake_data.fakeStore1

class FakeStoreRepository : StoreRepository {

    private var shouldFail = false
    private val stores = mutableMapOf<String, Store>()

    fun setShouldFail(value: Boolean) {
        shouldFail = value
    }

    fun addStore(store: Store, id: String) {
        stores[id] = store
    }

    override suspend fun getStoreDetailById(id: String): Result<Store> {
        return if (shouldFail) {
            Result.failure(Exception("Network error"))
        } else {
            stores[id]?.let { Result.success(it) }
                ?: Result.failure(Exception("Store not found"))
        }
    }

}
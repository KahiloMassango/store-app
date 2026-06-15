package com.example.store.core.data.repository

import com.example.store.core.model.store.Store

interface StoreRepository {
    suspend fun getStoreDetailById(id: String): Result<Store>
}
package com.example.store.core.data.repository

import com.example.store.core.model.search.RecentSearch
import kotlinx.coroutines.flow.Flow

interface RecentSearchRepository {
    suspend fun saveSearch(query: String)
    fun getRecentSearches(): Flow<List<RecentSearch>>
    suspend fun deleteRecentSearchById(id: Int)
    suspend fun clearAllRecentSearches()

}
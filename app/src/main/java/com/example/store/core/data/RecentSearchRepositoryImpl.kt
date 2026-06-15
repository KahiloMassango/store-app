package com.example.store.core.data

import com.example.store.core.data.repository.RecentSearchRepository
import com.example.store.core.database.dao.RecentSearchDao
import com.example.store.core.database.model.RecentSearchEntity
import com.example.store.core.database.model.asExternalModel
import com.example.store.core.model.search.RecentSearch
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RecentSearchRepositoryImpl(
    private val recentSearchDao: RecentSearchDao

): RecentSearchRepository {
    override suspend fun saveSearch(query: String) {
        recentSearchDao.insertRecentSearch(RecentSearchEntity(query = query))
    }

    override fun getRecentSearches(): Flow<List<RecentSearch>> {
        return recentSearchDao.getRecentSearches().map { list -> list.map { it.asExternalModel() } }
    }

    override suspend fun deleteRecentSearchById(id: Int) {
        recentSearchDao.deleteRecentSearchById(id)
    }

    override suspend fun clearAllRecentSearches() {
        recentSearchDao.deleteAllRecentSearches()
    }
}
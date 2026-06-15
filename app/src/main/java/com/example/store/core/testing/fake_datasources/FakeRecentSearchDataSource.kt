package com.example.store.core.testing.fake_datasources

import com.example.store.core.database.dao.RecentSearchDao
import com.example.store.core.database.model.RecentSearchEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeRecentSearchDataSource : RecentSearchDao {

    private val recentSearchesList = mutableListOf<RecentSearchEntity>()

    override suspend fun insertRecentSearch(recentSearch: RecentSearchEntity) {
        recentSearchesList.add(recentSearch)
    }

    override fun getRecentSearches(): Flow<List<RecentSearchEntity>> {
        return flowOf(recentSearchesList)
    }

    override suspend fun deleteRecentSearchById(id: Int) {
        recentSearchesList.removeIf { it.id == id }
    }

    override suspend fun deleteAllRecentSearches() {
        recentSearchesList.clear()
    }
}
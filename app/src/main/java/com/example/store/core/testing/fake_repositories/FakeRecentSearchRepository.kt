package com.example.store.core.testing.fake_repositories

import com.example.store.core.data.repository.RecentSearchRepository
import com.example.store.core.model.search.RecentSearch
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.update

class FakeRecentSearchRepository: RecentSearchRepository {

    private val recentSearches = MutableStateFlow<List<RecentSearch>>(emptyList())

    // Just for test purposes
    fun addRecentSearch(recentSearch: RecentSearch) {
        recentSearches.update {
            it + recentSearch
        }
    }


    override suspend fun saveSearch(query: String) {
        recentSearches.update {
            it + RecentSearch(query = query)
        }
    }

    override fun getRecentSearches(): Flow<List<RecentSearch>> {
        return recentSearches.asStateFlow()
    }

    override suspend fun deleteRecentSearchById(id: Int) {
        recentSearches.update {
            it.filter { it.id != id }
        }
    }

    override suspend fun clearAllRecentSearches() {
        recentSearches.update { emptyList() }
    }
}
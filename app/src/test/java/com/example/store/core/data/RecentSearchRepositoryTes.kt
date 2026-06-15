package com.example.store.core.data

import com.example.store.core.data.repository.RecentSearchRepository
import com.example.store.core.testing.fake_datasources.FakeRecentSearchDataSource
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class RecentSearchRepositoryTes {

    private lateinit var repository: RecentSearchRepository

    @Before
    fun setup() {
        val dataSource = FakeRecentSearchDataSource()
        repository = RecentSearchRepositoryImpl(dataSource)
    }

    @Test
    fun `recent search is saved correctly`() = runTest {
        val query = "test query"
        repository.saveSearch(query)

        val result = repository.getRecentSearches().first()

        assertEquals(query, result.first().query)

    }

    @Test
    fun `recent search is deleted correctly`() = runTest {
        val query = "test query"
        repository.saveSearch(query)

        val recentSearches = repository.getRecentSearches().first()
        val id = recentSearches.first().id

        repository.deleteRecentSearchById(id)

        val result = repository.getRecentSearches().first()

        assertTrue(result.isEmpty())
    }

    @Test
    fun `clear all recent searches`() = runTest {
        val query = "test query"
        repository.saveSearch(query)

       repository.clearAllRecentSearches()
        val result = repository.getRecentSearches().first()

        assertTrue(result.isEmpty())

    }
}
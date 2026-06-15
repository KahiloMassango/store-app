package com.example.store.features.search

import com.example.store.core.model.search.RecentSearch
import com.example.store.core.testing.fake_data.product.fakeProduct1
import com.example.store.core.testing.fake_data.product.fakeProduct2
import com.example.store.core.testing.fake_repositories.FakeProductRepository
import com.example.store.core.testing.fake_repositories.FakeRecentSearchRepository
import com.example.store.core.testing.util.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SearchViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var productRepository: FakeProductRepository
    private lateinit var recentSearchRepository: FakeRecentSearchRepository
    private lateinit var viewModel: SearchViewModel


    @Before
    fun setup() {
        productRepository = FakeProductRepository()
        recentSearchRepository = FakeRecentSearchRepository()
        viewModel = SearchViewModel(recentSearchRepository, productRepository)
    }

    @Test
    fun `searchQuery is initially empty`() {
        assertTrue(viewModel.searchQuery.isEmpty())
    }

    @Test
    fun `searchResults is initially empty`() = runTest {
        assertTrue(viewModel.searchResults.value.isEmpty())
    }

    @Test
    fun `recentSearchQueries is initially empty`() = runTest {
        assertTrue(viewModel.recentSearchQueries.value.isEmpty())
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `saveSearchQuery saves query`() = runTest {
        val testQuery = RecentSearch(id = 0, query = "test query")
        viewModel.updateSearchQuery(testQuery.query)

        viewModel.saveSearchQuery()
        backgroundScope.launch(UnconfinedTestDispatcher()) { viewModel.recentSearchQueries.collect {} }

        assertEquals(listOf(testQuery), viewModel.recentSearchQueries.value)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `deleteRecentSearchById deletes query`() = runTest {
        val testQuery = RecentSearch(id = 1, query = "test query1")
        recentSearchRepository.addRecentSearch(testQuery)

        viewModel.deleteRecentSearchById(testQuery.id)
        backgroundScope.launch(UnconfinedTestDispatcher()) { viewModel.recentSearchQueries.collect {} }

        assertTrue(viewModel.recentSearchQueries.value.isEmpty())
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `clearAllRecentSearches deletes all queries`() = runTest {
        recentSearchRepository.addRecentSearch(RecentSearch(id = 1, query = "test query1"))

        viewModel.clearAllRecentSearches()
        backgroundScope.launch(UnconfinedTestDispatcher()) { viewModel.recentSearchQueries.collect {} }

        assertTrue(viewModel.recentSearchQueries.value.isEmpty())
    }

    @Test
    fun `updateSearchQuery updates searchQuery`() {
        val testQuery = "test query"
        viewModel.updateSearchQuery(testQuery)

        assertEquals(testQuery, viewModel.searchQuery)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `searchResult updates when searchQuery changes`() = runTest {
        productRepository.setProducts(listOf(fakeProduct1))

        backgroundScope.launch(UnconfinedTestDispatcher()) { viewModel.searchResults.collect {} }
        viewModel.updateSearchQuery(fakeProduct1.name)
        advanceTimeBy(210)

        assertEquals(listOf(fakeProduct1), viewModel.searchResults.first())

    }


}
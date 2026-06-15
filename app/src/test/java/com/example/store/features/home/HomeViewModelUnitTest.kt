package com.example.store.features.home

import com.example.store.core.testing.fake_data.product.fakeProduct1
import com.example.store.core.testing.fake_data.product.fakeProduct2
import com.example.store.core.testing.fake_data.product.fakeProduct3
import com.example.store.core.testing.fake_repositories.FakeProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class HomeViewModelUnitTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = UnconfinedTestDispatcher()
    private lateinit var repository: FakeProductRepository
    private lateinit var viewModel: HomeViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        repository = FakeProductRepository()
        viewModel = HomeViewModel(repository)

    }

    @Test
    fun `loadProducts emits Success when repository returns data`() = runTest {
        val fakeProducts = listOf(fakeProduct1, fakeProduct2, fakeProduct3)
        repository.setProducts(fakeProducts)

        viewModel.loadProducts()

        assertEquals(HomeUiState.Success(fakeProducts), viewModel.uiState.first())
    }

    @Test
    fun `loadProducts emits Error when repository returns failure`() = runTest {
        // Given
        repository.setShouldFail(true)

        // When
         viewModel.loadProducts()

        // Then
        assertEquals(HomeUiState.Error("Network error"), viewModel.uiState.first())
    }
}
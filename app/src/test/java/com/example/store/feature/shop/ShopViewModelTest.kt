package com.example.store.feature.shop

import androidx.lifecycle.SavedStateHandle
import com.example.store.core.testing.fake_data.product.fakeProduct1
import com.example.store.core.testing.fake_data.product.fakeProduct2
import com.example.store.core.testing.fake_repositories.FakeProductRepository
import com.example.store.core.testing.util.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ShopViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var savedState: SavedStateHandle
    private lateinit var repository: FakeProductRepository
    private lateinit var viewModel: ShopViewModel

    @Before
    fun setup() {
        savedState = SavedStateHandle()
        repository = FakeProductRepository()
        viewModel = ShopViewModel(repository, savedState)
    }

    @Test
    fun `getGender returns correct gender from SavedStateHandle`() {
        savedState["gender"] = "test gender"

        assertEquals("test gender", viewModel.gender.value)
    }

    @Test
    fun `getGender returns empty string when gender is not in SavedStateHandle`() {
        assertEquals("", viewModel.gender.value)
    }

    @Test
    fun `getCategory returns correct category from SavedStateHandle`() {
        savedState["category"] = "test category"

        assertEquals("test category", viewModel.category.value)
    }

    @Test
    fun `getCategory returns empty string when category is not in SavedStateHandle`() {
        assertEquals("", viewModel.category.value)
    }


    @Test
    fun `uiState is Success when getProducts() is successful`() = runTest {
        val products = listOf(fakeProduct1, fakeProduct2)
        repository.setProducts(products)

        viewModel.getProducts()
        backgroundScope.launch(UnconfinedTestDispatcher()) { viewModel.uiState.collect {} }

        assertEquals(ShopUiState.Success(products), viewModel.uiState.value)
    }

    @Test
    fun `uiState is error when getProducts fails`() = runTest {
        repository.setShouldFail(true)

        viewModel.getProducts()
        backgroundScope.launch(UnconfinedTestDispatcher()) { viewModel.uiState.collect {} }

        assertEquals(ShopUiState.Error, viewModel.uiState.value)
    }


    @Test
    fun `updateOrderOption updates orderBy in Success state`() = runTest {
        viewModel.updateOrderOption("test order")

        assertEquals("test order", (viewModel.uiState.value as ShopUiState.Success).orderBy)
    }

    @Test
    fun `updateOrderOption throws exception when uiState is not Success`() {

        repository.setShouldFail(true)
        viewModel.getProducts()
        assertThrows(ClassCastException::class.java) {
            viewModel.updateOrderOption("test order")
        }
    }



    @Test
    fun `getProducts handles empty product list`() = runTest {

        viewModel.getProducts()
        backgroundScope.launch(UnconfinedTestDispatcher()) { viewModel.uiState.collect {} }

        assertEquals(ShopUiState.Success(emptyList()), viewModel.uiState.value)

    }


}
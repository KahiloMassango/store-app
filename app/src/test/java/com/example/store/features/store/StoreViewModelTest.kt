package com.example.store.features.store

import androidx.lifecycle.SavedStateHandle
import com.example.store.core.testing.fake_data.fakeStore1
import com.example.store.core.testing.fake_repositories.FakeStoreRepository
import com.example.store.core.testing.util.MainDispatcherRule
import com.example.store.features.store.model.StoreUiState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

@OptIn(ExperimentalCoroutinesApi::class)
class StoreViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var savedState: SavedStateHandle
    private lateinit var repository: FakeStoreRepository
    private lateinit var viewModel: StoreViewModel

    @Before
    fun setup() {
        savedState = SavedStateHandle()
        repository = FakeStoreRepository()
        viewModel = StoreViewModel(repository, savedState)
    }

    @Test
    fun `correct storeId is retrieved from SavedStateHandle`() = runTest {
        savedState["id"] = "testId"

        assertEquals("testId", viewModel.storeId.value)
    }

    @Test
    fun `uiState is Success when loadStore() is successful`() = runTest {
        repository.addStore(fakeStore1, "testId")
        savedState["id"] = "testId"

        viewModel.loadStore()
        backgroundScope.launch(UnconfinedTestDispatcher()) { viewModel.uiState.collect {} }

        assertEquals(StoreUiState.Success(fakeStore1), viewModel.uiState.value)
    }

    @Test
    fun `uiState is Error when loadStore() fails`() = runTest {
        repository.setShouldFail(true)

        viewModel.loadStore()
        backgroundScope.launch(UnconfinedTestDispatcher()) { viewModel.uiState.collect {} }

        assertEquals(StoreUiState.Error("Network error"), viewModel.uiState.value)
    }

}
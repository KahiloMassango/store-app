package com.example.store.features.discover

import com.example.store.core.testing.fake_repositories.FakeGenderRepository
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*


class DiscoverViewModelTest {

    private lateinit var repository: FakeGenderRepository
    private lateinit var viewModel: DiscoverViewModel

    @Before
    fun setUp() {
        repository = FakeGenderRepository()
        viewModel = DiscoverViewModel(repository)
    }

    @Test
    fun `uiState is initially empty`() = runTest {

        assertEquals(emptyMap<String, List<String>>(), viewModel.uiState.value)
    }
}
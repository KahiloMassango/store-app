package com.example.store.core.data

import com.example.store.core.network.model.asExternalModel
import com.example.store.core.testing.fake_data.fakeStoreDtoRes1
import com.example.store.core.testing.fake_datasources.FakeStoreNetworkDataSource
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class StoreRepositoryTest {

    private lateinit var fakeNetworkDataSource: FakeStoreNetworkDataSource
    private lateinit var repository: StoreRepositoryImpl

    @Before
    fun setUp() {
        fakeNetworkDataSource = FakeStoreNetworkDataSource()
        repository = StoreRepositoryImpl(fakeNetworkDataSource)
    }

    @Test
    fun `given valid store id when getStoreDetailById then return store details`() = runTest {
        val store = repository.getStoreDetailById("1")
        assertTrue(store.isSuccess)
        assertEquals(fakeStoreDtoRes1.asExternalModel(), store.getOrNull())
    }

    @Test
    fun `given invalid store id whe getStoreDetailById then return failure`() = runTest {
        val store = repository.getStoreDetailById("Invalid id")
        assertTrue(store.isFailure)
    }

    @Test
    fun `given network error when getStoreDetailById then return failure`() = runTest {
        fakeNetworkDataSource.setShouldFail(true)
        val store = repository.getStoreDetailById("1")
        assertTrue(store.isFailure)

    }
}
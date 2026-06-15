package com.example.store.core.data

import com.example.store.core.testing.fake_data.fakeAddress1
import com.example.store.core.testing.fake_data.fakeAddress2
import com.example.store.core.testing.fake_data.fakeAddressEntity1
import com.example.store.core.testing.fake_data.fakeAddressEntity2
import com.example.store.core.testing.fake_datasources.FakeAddressLocalDataSource
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class DefaultAddressRepositoryTest {

    private lateinit var repository: AddressRepositoryImpl
    private lateinit var fakeDataSource: FakeAddressLocalDataSource

    @Before
    fun setup() {
        fakeDataSource = FakeAddressLocalDataSource()
        repository = AddressRepositoryImpl(fakeDataSource)
    }

    @Test
    fun getAddressesStream_emitsAddresses_whenAddressesExist() = runTest {
        fakeDataSource.insertAddress(fakeAddressEntity1)

        val result = repository.getAddressesStream().first()

        assertEquals(1, result.size)
        assertEquals(fakeAddressEntity1.id, result.first().id)
    }

    @Test
    fun getLastAddedAddress_returnsLastAddress_whenAddressesExist() = runTest {


        fakeDataSource.insertAddress(fakeAddressEntity1)
        fakeDataSource.insertAddress(fakeAddressEntity2)

        val lastAddress = repository.getLastAddedAddress()
        assertNotNull(lastAddress)
        assertEquals(fakeAddress2.id, lastAddress?.id)
    }

    @Test
    fun getLastAddedAddress_returnsNull_whenNoAddressesExist() {
        val lastAddress = repository.getLastAddedAddress()
        assertNull(lastAddress)
    }

    @Test
    fun addAddress_savesAddress_whenValidAddressProvided() = runTest {
        repository.addAddress(fakeAddress1)

        val storedAddresses = repository.getAddressesStream().first()

        assertEquals(1, storedAddresses.size)
        assertEquals(fakeAddress1.receiverName, storedAddresses.first().receiverName)
    }

    @Test
    fun deleteAddressById_removesAddress_whenAddressExists() = runTest {
        fakeDataSource.insertAddress(fakeAddressEntity1)
        repository.deleteAddressById(fakeAddressEntity1.id)

        val remainingAddresses = repository.getAddressesStream().first()
        assertTrue(remainingAddresses.isEmpty())
    }

    @Test
    fun deleteAddressById_doesNothing_whenAddressDoesNotExist() = runTest {
        repository.deleteAddressById(99) // Non-existing ID

        val addresses = repository.getAddressesStream().first()
        assertTrue(addresses.isEmpty())
    }
}

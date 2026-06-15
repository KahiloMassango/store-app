package com.example.store.core.testing.fake_datasources

import com.example.store.core.database.dao.AddressesDao
import com.example.store.core.database.model.AddressEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeAddressLocalDataSource: AddressesDao {

    private val addresses = mutableListOf<AddressEntity>()

    override fun getAddressesStream(): Flow<List<AddressEntity>> {
        return flowOf(addresses)
    }

    override fun getLastAddedAddress(): AddressEntity? {
        return addresses.lastOrNull()
    }

    override fun deleteAddressById(id: Int) {
        addresses.removeIf { it.id == id }
    }

    override suspend fun insertAddress(address: AddressEntity) {
        addresses.add(address)
    }
}
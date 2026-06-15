package com.example.store.core.data

import com.example.store.core.data.model.asEntity
import com.example.store.core.data.repository.AddressRepository
import com.example.store.core.database.dao.AddressesDao
import com.example.store.core.database.model.asExternalModel
import com.example.store.core.model.Address
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AddressRepositoryImpl(
    private val addressesDao: AddressesDao
): AddressRepository {

    override fun getAddressesStream(): Flow<List<Address>> {
        return addressesDao.getAddressesStream().map { list -> list.map { it.asExternalModel() } }
    }

    override fun getLastAddedAddress(): Address? {
        return addressesDao.getLastAddedAddress()?.asExternalModel()
    }


    override suspend fun addAddress(address: Address) {
        addressesDao.insertAddress(address.asEntity())
    }

    override suspend fun deleteAddressById(id: Int) {
        addressesDao.deleteAddressById(id)
    }
}
package com.example.store.core.data.repository

import com.example.store.core.model.Address
import kotlinx.coroutines.flow.Flow

interface AddressRepository {
    fun getAddressesStream(): Flow<List<Address>>
    fun getLastAddedAddress(): Address?
    suspend fun addAddress(address: Address)
    suspend fun deleteAddressById(id: Int)
}
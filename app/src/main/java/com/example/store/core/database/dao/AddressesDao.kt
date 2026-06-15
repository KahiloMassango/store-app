package com.example.store.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.store.core.database.model.AddressEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AddressesDao {

    @Query("SELECT * FROM addresses ORDER BY id DESC")
    fun getAddressesStream(): Flow<List<AddressEntity>>

    @Query("SELECT * FROM addresses ORDER BY id DESC LIMIT 1")
    fun getLastAddedAddress(): AddressEntity?

    @Query("DELETE FROM addresses WHERE id = :id")
    fun deleteAddressById(id: Int)

    @Insert
    suspend fun insertAddress(address: AddressEntity)

}
package com.example.store.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.store.core.model.Address
import com.example.store.core.model.AddressLine
import com.example.store.core.model.AddressType

@Entity(tableName = "addresses")
data class AddressEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "receiver_name")
    val receiverName: String,

    @ColumnInfo(name = "phone_number")
    val phoneNumber: String,
    @ColumnInfo(name = "address_type")
    val addressType: AddressType,
    @ColumnInfo(name = "short_name")
    val shortName: String,
    @ColumnInfo(name = "address_line")
    val addressLine: String,
    val latitude: Double,
    val longitude: Double,
)

fun AddressEntity.asExternalModel() = Address(
    id = id,
    receiverName = receiverName,
    phoneNumber = phoneNumber,
    addressType = addressType,
    addressLine = AddressLine(shortName, addressLine),
    latitude = latitude,
    longitude = longitude
)

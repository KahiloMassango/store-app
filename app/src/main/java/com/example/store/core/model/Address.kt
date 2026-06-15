package com.example.store.core.model
import com.example.store.R

import androidx.annotation.DrawableRes

data class Address(
    val id: Int = 0,
    val receiverName: String,
    val phoneNumber: String,
    val addressType: AddressType,
    val addressLine: AddressLine,
    val latitude: Double,
    val longitude: Double,
)

data class AddressLine(
    val shortName: String,
    val address: String,
)

data class MapCoordinates(
    val latitude: Double,
    val longitude: Double
)

enum class AddressType(
    val description: String,
    @DrawableRes
    val icon: Int

) {
    HOME("Casa", R.drawable.ic_home),
    WORK("Trabalho",R.drawable.ic_briefcase),
    OTHER("Outro", R.drawable.ic_location)
}

package com.example.store.core.network.model.geocode

import com.example.store.core.model.Location
import com.google.gson.annotations.SerializedName

data class LocationResponse(
    @SerializedName("display_name")
    val displayName: String,
    val lat: String,
    val lon: String,
)

fun LocationResponse.asExternalModel(): Location =
    Location(
        addressLine = this.displayName,
        latitude = this.lat.toDouble(),
        longitude = this.lon.toDouble()
    )

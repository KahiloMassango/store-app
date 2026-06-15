package com.example.store.core.data.repository

import com.example.store.core.model.AddressLine
import com.example.store.core.model.Location
import com.example.store.core.model.MapCoordinates

interface LocationRepository {

    suspend fun getLocationName(coordinates: MapCoordinates): AddressLine

    suspend fun searchLocation(query: String): List<Location>

    suspend fun getLocationDistance(
        origin: MapCoordinates,
        destination: MapCoordinates
    ): Double?
}
package com.example.store.core.testing.fake_repositories

import com.example.store.core.data.repository.LocationRepository
import com.example.store.core.model.AddressLine
import com.example.store.core.model.Location
import com.example.store.core.model.MapCoordinates

class FakeLocationRepository : LocationRepository {

    private val fakeLocations = mutableListOf<Location>()
    private val fakeAddressLines = mutableListOf<AddressLine>()

    fun setLocations(locations: List<Location>) {
        fakeLocations.clear()
        fakeLocations.addAll(locations)
    }

    fun setAddressLines(addressLines: List<AddressLine>) {
        fakeAddressLines.clear()
        fakeAddressLines.addAll(addressLines)
    }

    override suspend fun getLocationName(coordinates: MapCoordinates): AddressLine {
        return fakeAddressLines.firstOrNull() ?: throw Exception("Address not found")
    }

    override suspend fun searchLocation(query: String): List<Location> {
        return fakeLocations.filter { it.addressLine.contains(query, ignoreCase = true) }
    }

    override suspend fun getLocationDistance(
        origin: MapCoordinates,
        destination: MapCoordinates
    ): Double? {
        return Math.sqrt(
            Math.pow(destination.latitude - origin.latitude, 2.0) +
                    Math.pow(destination.longitude - origin.longitude, 2.0)
        ) * 111 // Approximate conversion to kilometers
    }
}

package com.example.store.core.data

import android.location.Geocoder
import android.os.Build
import android.util.Log
import com.example.store.core.data.repository.LocationRepository
import com.example.store.core.model.AddressLine
import com.example.store.core.model.Location
import com.example.store.core.model.MapCoordinates
import com.example.store.core.network.model.geocode.asExternalModel
import com.example.store.core.network.retrofit.DistanceApiService
import com.example.store.core.network.retrofit.GeocodeApiService
import com.google.android.gms.maps.model.LatLng
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class DefaultLocationRepository(
    private val locationApisService: GeocodeApiService,
    private val geocodeService: Geocoder,
    private val distanceMatrixService: DistanceApiService
) : LocationRepository {


    @Suppress("DEPRECATION")
    override suspend fun getLocationName(coordinates: MapCoordinates): AddressLine {
        return try {
            when {
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> {
                    suspendCoroutine { continuation ->
                        geocodeService
                            .getFromLocation(
                                coordinates.latitude,
                                coordinates.longitude,
                                1
                            ) { result ->
                                val response = result.firstOrNull()
                                val addressLine = AddressLine(
                                    shortName = "${response?.locality}, ${response?.adminArea}",
                                    address = response?.getAddressLine(0) ?: "Sem nome",
                                )
                                continuation.resume(addressLine)
                            }
                    }
                }

                else -> {
                    val response = geocodeService.getFromLocation(
                        coordinates.latitude,
                        coordinates.longitude,
                        1
                    )?.firstOrNull()
                    AddressLine(
                        shortName = "${response?.locality}, ${response?.adminArea}",
                        address = response?.getAddressLine(0) ?: "Sem nome",
                    )
                }
            }

        } catch (e: Exception) {
            Log.d("LocationRepository", "getLocationName: $e")
            AddressLine(
                shortName = "Sem nome",
                address = "Endereço sem nome",
            )
        }
    }

    override suspend fun searchLocation(query: String): List<Location> {
        return locationApisService.getLocationsByName(query)
            .map { it.asExternalModel() }
    }

    override suspend fun getLocationDistance(
        origin: MapCoordinates,
        destination: MapCoordinates
    ): Double? {
        val storeLocation = "${origin.latitude},${origin.longitude}"
        val userLocation = "${destination.latitude},${destination.longitude}"
        try {
            val distance = distanceMatrixService.getDistanceMatrix(storeLocation, userLocation)
            Log.d("repository", "getLocationDistance: $distance")
            return distance.rows[0].elements[0].distance?.text?.trim()?.removeSuffix("km")
                ?.toDouble()
        } catch (e: Exception) {
            Log.d("repository", "getLocationDistance: ${e.message}")
            return 0.0
        }

    }
}
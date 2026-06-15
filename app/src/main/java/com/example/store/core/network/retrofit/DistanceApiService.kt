package com.example.store.core.network.retrofit

import com.example.store.core.network.model.distance_matrix.DistanceRespone
import retrofit2.http.GET
import retrofit2.http.Query

interface DistanceApiService {

    @GET("maps/api/distancematrix/json?key=ocxmn1DKn1z5ij2nd21QSPmyVMhKWGAsbmKpKY6eoqUO1pPSOjSjhjgv8iMXqTrB")
    suspend fun getDistanceMatrix(
        @Query("origins") origins: String,
        @Query("destinations") destinations: String,
    ): DistanceRespone
}
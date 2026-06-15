package com.example.store.core.network.retrofit

import com.example.store.core.network.model.Response
import com.example.store.core.network.model.authentication.AuthenticationDtoRes
import com.example.store.core.network.model.authentication.LoginDtoReq
import com.example.store.core.network.model.authentication.RefreshTokenDtoReq
import com.example.store.core.network.model.product.ProductDtoRes
import com.example.store.core.network.model.product.ProductDetailDtoRes
import com.example.store.core.network.model.sync.LastUpdatedDtoRes
import com.example.store.core.network.model.sync.SyncMetadataDtoRes
import com.example.store.core.network.model.user.UserDtoReq
import com.example.store.core.network.model.user.UserDtoRes
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface PublicApiService {

    @POST("auth/login")
    suspend fun login(
        @Body request: LoginDtoReq
    ): Response<AuthenticationDtoRes>

    @POST("customer/create")
    suspend fun createAccount(
        @Body request: UserDtoReq
    ): Response<UserDtoRes>


    @POST("auth/refresh-token")
    suspend fun refreshToken(
        @Body request: RefreshTokenDtoReq
    ): Response<AuthenticationDtoRes>

    @GET("products/search")
    suspend fun searchProducts(@Query("query") gender: String): Response<List<ProductDtoRes>>

    @GET("products/filter")
    suspend fun getProducts(
        @Query("gender") gender: String?,
        @Query("category") category: String?
    ): Response<List<ProductDtoRes>>

    @GET("products/{id}")
    suspend fun getProductById(@Path("id") productId: String): Response<ProductDetailDtoRes>

    @GET("sync/last-updated")
    suspend fun fetchLastUpdated(): Response<LastUpdatedDtoRes>

    @GET("sync")
    suspend fun fetchSyncMetadata(): Response<SyncMetadataDtoRes>
}
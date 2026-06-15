package com.example.store.core.network.retrofit

import com.example.store.core.network.model.Response
import com.example.store.core.network.model.StoreDtoRes
import com.example.store.core.network.model.order.request.OrderDtoReq
import com.example.store.core.network.model.order.response.OrderDtoRes
import com.example.store.core.network.model.order.response.OrdersDtoRes
import com.example.store.core.network.model.user.UserDtoRes
import com.example.store.core.network.model.user.UserUpdateDtoReq
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface AuthenticatedApiService {

    //  USER
    @GET("customer")
    suspend fun getAccountDetails(): Response<UserDtoRes>

    @GET("auth/logout")
    suspend fun logout()

    @PUT("customer/update")
    suspend fun updateAccount(
        @Body request: UserUpdateDtoReq
    ): Response<UserDtoRes>

    @GET("stores/{id}")
    suspend fun getStoreDetail(@Path("id") id: String): Response<StoreDtoRes>

    @PUT("products/update/password")
    suspend fun updatePassword(@Body newPassword: String): Response<Unit>

    // ORDERS
    @POST("customer/orders")
    suspend fun placeOrder(@Body request: OrderDtoReq): Response<OrderDtoRes>

    @GET("customer/orders/{id}")
    suspend fun getOrderById(@Path("id") id: String): Response<OrderDtoRes>

    @GET("customer/orders")
    suspend fun getAllOrders(): Response<OrdersDtoRes>
}
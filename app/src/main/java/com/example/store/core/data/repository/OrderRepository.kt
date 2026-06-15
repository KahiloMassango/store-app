package com.example.store.core.data.repository

import com.example.store.core.model.cart.CartProductItem
import com.example.store.core.model.order.Order
import com.example.store.core.model.order.OrderStateSummary
import com.example.store.core.network.model.order.response.OrderDtoRes

interface OrderRepository {

    suspend fun getOrderById(id: String): Result<Order>

    suspend fun getMyOrders(): Result<OrderStateSummary>

    suspend fun placeOrder(
        total: Int,
        subTotal: Int,
        deliveryFee: Int,
        deliveryAddressName: String,
        latitude: Double,
        longitude: Double,
        paymentType: String,
        cartProductItems: List<CartProductItem>
    ): Result<Order>

}
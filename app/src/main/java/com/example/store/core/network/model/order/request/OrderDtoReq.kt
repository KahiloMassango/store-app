package com.example.store.core.network.model.order.request

data class OrderDtoReq(
    val subTotal: Int,
    val deliveryFee: Int,
    val total: Int,
    val deliveryAddressName: String,
    val latitude: Double,
    val longitude: Double,
    val paymentType: String,
    val orderItems: List<OrderItemDtoReq>
)

data class OrderItemDtoReq(
    val productItemId: String,
    val quantity: Int,
)

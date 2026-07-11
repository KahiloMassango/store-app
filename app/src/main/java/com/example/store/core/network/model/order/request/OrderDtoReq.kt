package com.example.store.core.network.model.order.request

data class OrderDtoReq(
    val subTotal: Int,
    val deliveryFeeAmount: Double,
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

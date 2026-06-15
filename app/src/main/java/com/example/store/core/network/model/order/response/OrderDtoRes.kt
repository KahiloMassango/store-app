package com.example.store.core.network.model.order.response

import com.example.store.core.model.order.Order

data class OrderDtoRes(
    val id: String,
    val storeName: String,
    val date: String,
    val total: Int,
    val orderTotalItems: Int,
    val subTotal: Int,
    val deliveryFee: Int,
    val status: String,
    val deliveryAddressName: String,
    val paymentType: String,
    val deliveryMethod: String,
    val orderItems: List<OrderItemDtoRes>
)

data class OrdersDtoRes(
    val delivered: List<OrderDtoRes>,
    val pending: List<OrderDtoRes>,
    val canceled: List<OrderDtoRes>
)

fun OrderDtoRes.asExternalModel() = Order(
    id = id,
    total = total,
    date = date,
    storeName = storeName,
    totalItems = orderTotalItems,
    status = status,
    subTotal = subTotal,
    deliveryFee = deliveryFee,
    deliveryAddress = deliveryAddressName,
    paymentType = paymentType,
    orderItems = orderItems.map { it.asExternalModel() },
)
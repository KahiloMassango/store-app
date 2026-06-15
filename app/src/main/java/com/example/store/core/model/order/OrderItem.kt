package com.example.store.core.model.order

data class OrderItem(
    val image: String,
    val color: String?,
    val size: String?,
    val quantity: Int,
    val price: Int
)


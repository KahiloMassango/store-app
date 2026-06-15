package com.example.store.core.model.store

import com.example.store.core.model.product.Product

data class Store(
    val name: String,
    val description: String,
    val logoUrl: String,
    val offersDelivery: Boolean,
    val nif: String,
    val memberSince: String,
    val address: String,
    val latitude: Double,
    val longitude: Double,
    val openingTime: String,
    val closingTime: String,
    val totalProducts: Int,
    val totalCompletedOrders: Int,
    val products: List<Product>
)

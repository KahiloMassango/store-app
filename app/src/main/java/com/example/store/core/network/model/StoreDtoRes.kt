package com.example.store.core.network.model

import com.example.store.core.model.store.Store
import com.example.store.core.network.model.product.ProductDtoRes
import com.example.store.core.network.model.product.asExternalModel

data class StoreDtoRes(
    val name: String,
    val logoUrl: String,
    val description: String,
    val offersDelivery: Boolean,
    val nif: String,
    val memberSince: String,
    val openingTime: String,
    val closingTime: String,
    val address: String,
    val latitude: Double,
    val longitude: Double,
    val totalProducts: Int,
    val totalCompletedOrders: Int,
    val products: List<ProductDtoRes>
)


fun StoreDtoRes.asExternalModel() = Store(
    name = name,
    description = description,
    logoUrl = logoUrl,
    offersDelivery = offersDelivery,
    nif = nif,
    memberSince = memberSince,
    address = address,
    latitude = latitude,
    longitude = longitude,
    openingTime = openingTime,
    closingTime = closingTime,
    totalProducts = totalProducts,
    totalCompletedOrders = totalCompletedOrders,
    products = products.map { it.asExternalModel() }
)

package com.example.store.core.model.product


data class ProductItem(
    val id: String,
    val color: String?,
    val image: String?,
    val price: Int,
    val size: String?,
    val stockQuantity: Int
)
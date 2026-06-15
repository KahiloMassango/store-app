package com.example.store.core.model.product


data class Product(
    val id: String  ,
    val description: String,
    val image: String,
    val storeId: String,
    val storeName: String,
    val name: String,
    val minPrice: Int
)
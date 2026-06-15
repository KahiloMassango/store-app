package com.example.store.core.model

data class FavoriteProduct(
    val id: String,
    val name: String,
    val storeName: String,
    val price: Int,
    val imageUrl: String,
    val color: String,
    val size: String,
    val avgRating: Int,
    val totalRatings: Int
)

val favoriteProduct = FavoriteProduct(
    id = "id",
    name = "T-Shirt Sailing",
    storeName = "Mango",
    price = 2500,
    imageUrl = "ddd",
    color = "Preto",
    size = "XL",
    avgRating = 3,
    totalRatings = 32
)

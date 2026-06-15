package com.example.store.core.model.cart

data class CartProductItem(
    val id: String,
    val productId: String,
    val name: String,
    val price: Int,
    val imageUrl: String,
    val color: String?,
    val size: String?,
    val quantity: Int,
    val stockQuantity: Int
)

val cartProductItem = CartProductItem(
    id = "11",
    productId = "",
    name = "T-Shirt Sailing",
    price = 2500,
    imageUrl = "",
    color = "Preto",
    size = "XXL",
    quantity = 1,
    stockQuantity = 9
)


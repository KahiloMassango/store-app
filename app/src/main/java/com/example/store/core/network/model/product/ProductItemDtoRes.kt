package com.example.store.core.network.model.product

import com.example.store.core.model.product.ProductItem


data class ProductItemDtoRes(
    val id: String,
    val stockQuantity: Int,
    val price: Int,
    val imageUrl: String?,
    val color: String?,
    val size: String?
)

fun ProductItemDtoRes.asExternalModel(): ProductItem =
    ProductItem(
        id = id,
        color = color,
        price = price,
        size = size,
        stockQuantity = stockQuantity,
        image = imageUrl
    )
package com.example.store.core.data.model

import com.example.store.core.database.model.CartProductItemEntity
import com.example.store.core.model.product.ProductItem

fun ProductItem.asCartProductEntity(
    productName: String,
    imageUrl: String,
    productId: String
): CartProductItemEntity = CartProductItemEntity(
    id = id,
    productId = productId,
    name = productName,
    price = price,
    imageUrl = imageUrl,
    color = color,
    size = size,
    stockQuantity = stockQuantity
)

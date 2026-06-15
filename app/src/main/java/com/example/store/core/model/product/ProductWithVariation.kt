package com.example.store.core.model.product

import com.example.store.core.model.Category


data class ProductWithVariation(
    val id: String,
    val description: String,
    val image: String,
    val storeId: String,
    val storeName: String,
    val name: String,
    val category: Category,
    val productItems: List<ProductItem>
)

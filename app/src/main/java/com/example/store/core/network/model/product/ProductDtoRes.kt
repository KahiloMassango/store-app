package com.example.store.core.network.model.product

import com.example.store.core.model.product.Product
import com.example.store.core.model.product.ProductWithVariation
import com.example.store.core.network.model.sync.CategoryDtoRes
import com.example.store.core.network.model.sync.asExternalModel


data class ProductDtoRes(
    val id: String,
    val name: String,
    val imageUrl: String,
    val description: String,
    val storeId: String,
    val storeName: String,
    val minPrice: Int
)

fun ProductDtoRes.asExternalModel(): Product =
    Product(
        id = id,
        description = description,
        image = imageUrl,
        storeId = storeId,
        storeName = storeName,
        name = name,
        minPrice = minPrice
    )

data class ProductDetailDtoRes(
    val id: String,
    val name: String,
    val imageUrl: String,
    val description: String,
    val productItems: List<ProductItemDtoRes>,
    val storeId: String,
    val storeName: String,
    val category: CategoryDtoRes,
)

fun ProductDetailDtoRes.asExternalModel() = ProductWithVariation(
    id = id,
    description = description,
    image = imageUrl,
    storeId = storeId,
    storeName = storeName,
    name = name,
    productItems = productItems.map { it.asExternalModel() },
    category = category.asExternalModel()
)





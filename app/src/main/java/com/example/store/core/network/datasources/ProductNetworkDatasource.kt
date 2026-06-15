package com.example.store.core.network.datasources

import com.example.store.core.network.model.product.ProductDtoRes
import com.example.store.core.network.model.product.ProductDetailDtoRes

interface ProductNetworkDatasource {
    suspend fun getProductsByGenderAndCategory(
        gender: String?,
        category: String?
    ): Result<List<ProductDtoRes>>

    suspend fun getProductById(id: String): Result<ProductDetailDtoRes>

    suspend fun searchProducts(query: String): Result<List<ProductDtoRes>>
}
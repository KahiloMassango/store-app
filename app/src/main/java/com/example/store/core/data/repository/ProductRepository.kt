package com.example.store.core.data.repository

import com.example.store.core.model.product.Product
import com.example.store.core.model.Rating
import com.example.store.core.model.RatingInfo
import com.example.store.core.model.product.ProductWithVariation

interface ProductRepository {
    suspend fun getProducts(gender: String?, category: String?): Result<List<Product>>
    suspend fun getProductById(id: String): Result<ProductWithVariation>
    suspend fun searchProducts(query: String): Result<List<Product>>
    suspend fun getProductsByCategory(category: String): Result<List<Product>>
    //suspend fun getProductRatingInfo(productId: String): RatingInfo
    //suspend fun getProductRatings(productId: String): List<Rating>
}
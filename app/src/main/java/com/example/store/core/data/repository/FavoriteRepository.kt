package com.example.store.core.data.repository

import com.example.store.core.model.FavoriteProduct
import com.example.store.core.model.product.Product
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {

    fun getFavoriteProductsStream(): Flow<List<FavoriteProduct>>

    fun checkFavoriteProductStream(productId: String): Flow<Boolean>

    suspend fun addFavoriteProduct(product: Product)

    suspend fun removeFavoriteProduct(productId: String)

}
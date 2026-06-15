package com.example.store.core.data.repository

import com.example.store.core.model.cart.CartProductItem
import com.example.store.core.model.product.ProductItem
import kotlinx.coroutines.flow.Flow

interface CartRepository {

    fun getCartProductsStream(): Flow<List<CartProductItem>>
    fun getCartProducts(): List<CartProductItem>
    fun getCartProductsCountStream(): Flow<Int>
    fun getCartTotalStream(): Flow<Int>
    suspend fun removeCartProduct(id: String)
    suspend fun updateQuantity(id: String, quantity: Int)
    suspend fun clearCart()
    suspend fun addToCart(
        productName: String,
        productId: String,
        imageUrl: String,
        productItem: ProductItem
    )


}
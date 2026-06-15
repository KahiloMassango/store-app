package com.example.store.core.data

import com.example.store.core.data.model.asCartProductEntity
import com.example.store.core.data.repository.CartRepository
import com.example.store.core.database.dao.CartDao
import com.example.store.core.database.model.asExternalModel
import com.example.store.core.model.cart.CartProductItem
import com.example.store.core.model.product.ProductItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CartRepositoryImpl(
    private val cartDao: CartDao
) : CartRepository {

    override fun getCartProductsStream(): Flow<List<CartProductItem>> {
        return cartDao.getCartProductsFlow().map { list -> list.map { it.asExternalModel() } }
    }

    override fun getCartProducts(): List<CartProductItem> {
        return cartDao.getCartProducts().map { it.asExternalModel() }
    }

    override fun getCartProductsCountStream(): Flow<Int> {
        return cartDao.getCartProductCount()
    }

    override fun getCartTotalStream(): Flow<Int> {
        return cartDao.getCartTotalStream()
    }

    override suspend fun clearCart() {
        cartDao.clearCart()
    }

    override suspend fun addToCart(
        productName: String,
        productId: String,
        imageUrl: String,
        productItem: ProductItem
    ) {
        // if product already exists in cart with the same properties do not add
        val checkProduct = cartDao.getProductByID(productItem.id)
        if (checkProduct == null) {
            cartDao.insertCartProduct(
                productItem.asCartProductEntity(
                    productName = productName,
                    imageUrl = imageUrl,
                    productId = productId
                )
            )
        }
    }

    override suspend fun removeCartProduct(id: String) {
        cartDao.deleteCartProduct(id)
    }

    override suspend fun updateQuantity(id: String, quantity: Int) {
        if (quantity >= 0) {
            cartDao.updateQuantity(id, quantity)
        }
    }
}
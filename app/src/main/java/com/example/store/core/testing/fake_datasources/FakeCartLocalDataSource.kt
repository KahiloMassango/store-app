package com.example.store.core.testing.fake_datasources

import com.example.store.core.database.dao.CartDao
import com.example.store.core.database.model.CartProductItemEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeCartLocalDataSource: CartDao {

    private val cartProducts = mutableListOf<CartProductItemEntity>()

    override fun insertCartProduct(cartProductItemEntity: CartProductItemEntity) {
        cartProducts.add(cartProductItemEntity)
    }

    override suspend fun deleteCartProduct(id: String) {
        cartProducts.removeIf { it.id == id }
    }

    override suspend fun updateQuantity(id: String, quantity: Int) {
        val index = cartProducts.indexOfFirst { it.id == id }
        cartProducts[index] = cartProducts[index].copy(quantity = quantity)
    }

    override suspend fun getProductByID(id: String): CartProductItemEntity? {
        return cartProducts.find { it.id == id }
    }

    override suspend fun clearCart() {
        cartProducts.clear()
    }

    override fun getCartTotalStream(): Flow<Int> {
        return flowOf(cartProducts.sumOf { it.price * it.quantity })
    }

    override fun getCartProductCount(): Flow<Int> {
        return flowOf(cartProducts.size)
    }

    override fun getCartProductsFlow(): Flow<List<CartProductItemEntity>> {
        return flowOf(cartProducts)
    }

    override fun getCartProducts(): List<CartProductItemEntity> {
        return cartProducts.toList()
    }
}
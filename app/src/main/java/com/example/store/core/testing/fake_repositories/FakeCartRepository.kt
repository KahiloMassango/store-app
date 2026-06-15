package com.example.store.core.testing.fake_repositories

import com.example.store.core.data.repository.CartRepository
import com.example.store.core.model.cart.CartProductItem
import com.example.store.core.model.product.ProductItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

class FakeCartRepository: CartRepository {

    private val cartProducts = MutableStateFlow<List<CartProductItem>>(emptyList())

    override fun getCartProductsStream(): Flow<List<CartProductItem>> = cartProducts

    override fun getCartProducts(): List<CartProductItem> = cartProducts.value

    override fun getCartProductsCountStream(): Flow<Int> = cartProducts.map { it.size }

    override fun getCartTotalStream(): Flow<Int> = cartProducts.map { products ->
        products.sumOf { it.price * it.quantity }
    }

    override suspend fun removeCartProduct(id: String) {
        cartProducts.value = cartProducts.value.filterNot { it.id == id }
    }

    override suspend fun updateQuantity(id: String, quantity: Int) {
        cartProducts.value = cartProducts.value.map {
            if (it.id == id) it.copy(quantity = quantity) else it
        }
    }

    override suspend fun clearCart() {
        cartProducts.value = emptyList()
    }

    override suspend fun addToCart(
        productName: String,
        productId: String,
        imageUrl: String,
        productItem: ProductItem
    ) {
        val cartItem = CartProductItem(
            id = productItem.id,
            name = productName,
            imageUrl = imageUrl,
            price = productItem.price,
            quantity = 1,
            productId = productId,
            color = productItem.color,
            size = productItem.size,
            stockQuantity = productItem.stockQuantity,
        )
        cartProducts.update { it + cartItem }
    }

    // Helper function to add products to the cart for testing purposes
    fun addCartProducts(products: List<CartProductItem>) {
        cartProducts.update { it + products }
    }
}

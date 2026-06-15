package com.example.store.core.data

import com.example.store.core.database.model.CartProductItemEntity
import com.example.store.core.model.product.ProductItem
import com.example.store.core.testing.fake_datasources.FakeCartLocalDataSource
import junit.framework.TestCase.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class CartRepositoryTest {

    private lateinit var repository: CartRepositoryImpl
    private lateinit var fakeDataSource: FakeCartLocalDataSource

    @Before
    fun setup() {
        fakeDataSource = FakeCartLocalDataSource()
        repository = CartRepositoryImpl(fakeDataSource)
    }

    @Test
    fun getCartProductsStream_emitsCartProducts_whenCartHasItems() = runTest {
        val product = createCartProduct("1")
        fakeDataSource.insertCartProduct(product)

        val result = repository.getCartProductsStream().first()

        assertEquals(1, result.size)
        assertEquals(product.name, result.first().name)
    }

    @Test
    fun getCartProducts_returnsCartProducts_whenCartHasItems() {
        val product = createCartProduct("1")
        fakeDataSource.insertCartProduct(product)

        val result = repository.getCartProducts()

        assertEquals(1, result.size)
        assertEquals(product.name, result.first().name)
    }

    @Test
    fun getCartProductsCountStream_emitsCartProductCount_whenCartHasItems() = runTest {
        fakeDataSource.insertCartProduct(createCartProduct("1"))
        fakeDataSource.insertCartProduct(createCartProduct("2"))

        val result = repository.getCartProductsCountStream().first()

        assertEquals(2, result)
    }

    @Test
    fun getCartTotalStream_emitsTotalPrice_whenCartHasItems() = runTest {
        fakeDataSource.insertCartProduct(createCartProduct("1", price = 100, quantity = 2))
        fakeDataSource.insertCartProduct(createCartProduct("2", price = 50, quantity = 1))

        val result = repository.getCartTotalStream().first()

        assertEquals(250, result) // (100 * 2) + (50 * 1)
    }

    @Test
    fun clearCart_removesAllProducts_whenCalled() = runTest {
        fakeDataSource.insertCartProduct(createCartProduct("1"))
        repository.clearCart()

        val result = repository.getCartProducts()

        assertTrue(result.isEmpty())
    }

    @Test
    fun addToCart_addsNewProduct_whenProductDoesNotExist() = runTest {
        repository.addToCart("Product 1", "P1", "imageUrl", createProductItem("1"))

        val result = repository.getCartProducts()

        assertEquals(1, result.size)
    }

    @Test
    fun addToCart_doesNotAddProduct_whenProductAlreadyExists() = runTest {
        val product = createCartProduct("1")
        fakeDataSource.insertCartProduct(product)

        repository.addToCart("Product 1", "P1", "imageUrl", createProductItem("1"))

        val result = repository.getCartProducts()

        assertEquals(1, result.size) // Should not add duplicate
    }

    @Test
    fun removeCartProduct_removesProduct_whenProductExists() = runTest {
        fakeDataSource.insertCartProduct(createCartProduct("1"))
        repository.removeCartProduct("1")

        val result = repository.getCartProducts()

        assertTrue(result.isEmpty())
    }

    @Test
    fun updateQuantity_updatesQuantity_whenValidQuantityProvided() = runTest {
        fakeDataSource.insertCartProduct(createCartProduct("1", quantity = 1))
        repository.updateQuantity("1", 5)

        val result = repository.getCartProducts().first { it.id == "1" }

        assertEquals(5, result.quantity)
    }

    @Test
    fun updateQuantity_doesNotUpdate_whenQuantityIsNegative() = runTest {
        fakeDataSource.insertCartProduct(createCartProduct("1", quantity = 1))
        repository.updateQuantity("1", -2)

        val result = repository.getCartProducts().first { it.id == "1" }

        assertEquals(1, result.quantity) // Should remain unchanged
    }

    private fun createCartProduct(id: String, price: Int = 100, quantity: Int = 1) = CartProductItemEntity(
        id = id,
        productId = "P$id",
        name = "Product $id",
        price = price,
        imageUrl = "image_url",
        color = "Red",
        size = "M",
        quantity = quantity,
        stockQuantity = 10
    )

    private fun createProductItem(id: String) = ProductItem(
        id = id,
        price = 100,
        color = "Red",
        size = "M",
        stockQuantity = 10,
        image = "image_url"
    )
}

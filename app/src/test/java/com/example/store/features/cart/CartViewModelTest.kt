package com.example.store.features.cart

import com.example.store.core.model.cart.CartProductItem
import com.example.store.core.testing.fake_repositories.FakeCartRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test

class CartViewModelTest {

    private lateinit var repository: FakeCartRepository
    private lateinit var viewModel: CartViewModel
    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = UnconfinedTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        repository = FakeCartRepository()
        viewModel = CartViewModel(repository)
    }

    @Test
    fun `uiState initial value is empty`() = runTest {
        assertEquals(emptyList<CartProductItem>(), viewModel.uiState.value)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `uiState emit cart products when products are added to cart`() = runTest {
        val fakeProducts = listOf(createCartProduct(id ="1"), createCartProduct(id ="2"))
        repository.addCartProducts(fakeProducts)


        assertEquals(fakeProducts, viewModel.uiState.first())
    }


    @Test
    fun `cartTotal initial value is zero`() = runTest {
        assertEquals(0, viewModel.cartTotal.value)
    }

    @Test
    fun `cartTotal updates when products are added to cart`() = runTest {
        val fakeProducts = listOf(createCartProduct(id ="1"), createCartProduct(id ="2"))
        val total = fakeProducts.sumOf { it.price * it.quantity }
        repository.addCartProducts(fakeProducts)

        assertEquals(total, viewModel.cartTotal.first())
    }



    @Test
    fun `removeProductFromCart success`() = runTest {
        val fakeProduct = createCartProduct(id ="1")
        repository.addCartProducts(listOf(fakeProduct))

        viewModel.removeProductFromCart(fakeProduct.id)

        assertEquals(emptyList<CartProductItem>(), viewModel.uiState.first())
    }


    @Test
    fun `updateQuantity success`() = runTest {
        val fakeProduct1 = createCartProduct(id ="1")
        repository.addCartProducts(listOf(fakeProduct1))

        val newQuantity = 5
        viewModel.updateQuantity(fakeProduct1.id, newQuantity)

        val updatedProduct = fakeProduct1.copy(quantity = newQuantity)
        assertEquals(listOf(updatedProduct), viewModel.uiState.first())
    }


    @Test
    fun `updateQuantity does not update product when negative value is given`() = runTest {
        val fakeProduct1 = createCartProduct(id ="1")
        repository.addCartProducts(listOf(fakeProduct1))

        val newQuantity = -99
        viewModel.updateQuantity(fakeProduct1.id, newQuantity)

        val updatedProduct = fakeProduct1.copy(quantity = newQuantity)
        assertNotEquals(listOf(updatedProduct), viewModel.uiState.first())
    }


    private fun createCartProduct(
        id: String = "1",
        name: String = "Product 1",
        price: Int = 100,
        quantity: Int = 1,
        stockQuantity: Int = 1,
    ) = CartProductItem(
        id = id,
        productId = "product_id",
        name = name,
        price = price,
        imageUrl = "image_url",
        color = "color",
        size = "size",
        quantity = quantity,
        stockQuantity = stockQuantity
    )

}
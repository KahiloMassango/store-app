package com.example.store.core.data

import com.example.store.core.data.repository.OrderRepository
import com.example.store.core.model.cart.CartProductItem
import com.example.store.core.testing.fake_data.fakeOrderDtoRes1
import com.example.store.core.testing.fake_data.product.fakeProduct1
import com.example.store.core.testing.fake_datasources.FakeOrderNetworkDataSource
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class OrderRepositoryTest {

    private lateinit var fakeDataSource: FakeOrderNetworkDataSource
    private lateinit var repository: OrderRepository

    @Before
    fun setUp() {
        fakeDataSource = FakeOrderNetworkDataSource()
        repository = OrderRepositoryImpl(fakeDataSource)
    }

    @Test
    fun `getOrderById returns success when order exists`() = runTest {

        val fakeOrder = fakeOrderDtoRes1
        fakeDataSource.addFakeOrder(fakeOrder)


        val result = repository.getOrderById(fakeOrder.id)

        assertTrue(result.isSuccess)
        assertEquals(fakeOrder.id, result.getOrThrow().id)
    }

    @Test
    fun `getOrderById returns failure when order does not exist`() = runTest {

        val result = repository.getOrderById("invalid_id")

        assertTrue(result.isFailure)
        assertEquals("Order not found", result.exceptionOrNull()?.message)
    }

    @Test
    fun `getOrderById returns failure when network error occurs`() = runTest {
        val fakeOrder = fakeOrderDtoRes1
        fakeDataSource.addFakeOrder(fakeOrder)

        fakeDataSource.setForceNetworkError(true)

        val result = repository.getOrderById(fakeOrderDtoRes1.id)

        assertTrue(result.isFailure)
    }

    @Test
    fun `getMyOrders returns success when data is available`() = runTest {
        fakeDataSource.addFakeOrder(fakeOrderDtoRes1)

        val result = repository.getMyOrders()

        assertTrue(result.isSuccess)
    }

    @Test
    fun `getMyOrders returns failure when network error occurs`() = runTest {
        fakeDataSource.setForceNetworkError(true)

        val result = repository.getMyOrders()

        assertTrue(result.isFailure)
    }

    @Test
    fun `placeOrder returns success when order is placed`() = runTest {
        val result = repository.placeOrder(
            total = 3900,
            subTotal = 2400,
            deliveryFee = 1500,
            deliveryAddressName = "fake delivery address",
            latitude = 9.333,
            longitude = 15.555,
            paymentType = "payment",
            cartProductItems = listOf(
                CartProductItem(
                    id = "1",
                    quantity = 3,
                    color = "color",
                    size = "size",
                    imageUrl = "image url",
                    price = 2000,
                    name = "name",
                    stockQuantity = 1,
                    productId = fakeProduct1.id
                )
            )
        )

        assertTrue(result.isSuccess)
    }

    @Test
    fun `placeOrder returns failure when network error occurs`() = runTest {
        fakeDataSource.setForceNetworkError(true)

        val result = repository.placeOrder(
            total = 3900,
            subTotal = 2400,
            deliveryFee = 1500,
            deliveryAddressName = "fake delivery address",
            latitude = 9.333,
            longitude = 15.555,
            paymentType = "payment",
            cartProductItems = listOf(
                CartProductItem(
                    id = "1",
                    quantity = 3,
                    color = "color",
                    size = "size",
                    imageUrl = "image url",
                    price = 2000,
                    name = "name",
                    stockQuantity = 1,
                    productId = fakeProduct1.id
                )
            )
        )

        // Assert
        assertTrue(result.isFailure)
    }

}
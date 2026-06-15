package com.example.features.orders

import com.example.features.orders.model.OrdersUiState
import com.example.store.core.model.order.Order
import com.example.store.core.model.order.OrderStateSummary
import com.example.store.core.testing.fake_repositories.FakeOrderRepository
import com.example.store.core.testing.util.MainDispatcherRule
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MyOrdersViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()


    private lateinit var repository: FakeOrderRepository
    private lateinit var viewModel: MyOrdersViewModel

    @Before
    fun setup() {
        repository = FakeOrderRepository()
        viewModel = MyOrdersViewModel(repository)
    }

    @Test
    fun `loadOrders success`() {
        val pendingOrders = listOf(createOrder(), createOrder(id = "2"))
        repository.addOrders(pendingOrders)

        viewModel.loadOrders()

        assertEquals(
            OrdersUiState.Success(
                OrderStateSummary(
                    delivered = emptyList(),
                    pending = pendingOrders,
                    canceled = emptyList()
                )
            ),
            viewModel.uiState.value)
    }

    @Test
    fun `loadOrders failure with message`() {
        repository.setShouldFail(true)

        viewModel.loadOrders()

        assertEquals(OrdersUiState.Error("Network error"), viewModel.uiState.value)
    }


    @Test
    fun `Empty order list`() {
        viewModel.loadOrders()

        assertEquals(
            OrdersUiState.Success(
                OrderStateSummary(
                    delivered = emptyList(),
                    pending = emptyList(),
                    canceled = emptyList()
                )
            ),
            viewModel.uiState.value)
    }


    fun createOrder(id: String = "1", storeName: String = "fake store name", status: String = "pending") = Order(
        id = id,
        storeName = storeName,
        subTotal = 2400,
        deliveryFee = 1500,
        date = "06/06/2025",
        status = status,
        total = 3900,
        paymentType = "fake payment type",
        deliveryAddress = "fake delivery address name",
        orderItems = emptyList(),
        totalItems = 0,
    )

}
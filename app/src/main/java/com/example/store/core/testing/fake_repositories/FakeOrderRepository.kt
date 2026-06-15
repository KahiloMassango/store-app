package com.example.store.core.testing.fake_repositories

import com.example.store.core.data.repository.OrderRepository
import com.example.store.core.model.cart.CartProductItem
import com.example.store.core.model.order.Order
import com.example.store.core.model.order.OrderItem
import com.example.store.core.model.order.OrderStateSummary
import java.util.UUID

class FakeOrderRepository : OrderRepository {
    private var shouldFail = false
    private val orders = mutableListOf<Order>()

    fun setShouldFail(value: Boolean) {
        shouldFail = value
    }

    fun addOrders(orders: List<Order>) {
        this.orders.addAll(orders)
    }

    override suspend fun getOrderById(id: String): Result<Order> {
        return if (shouldFail) {
            Result.failure(Exception("Network error"))
        } else {
            orders.find { it.id == id }?.let { Result.success(it) }
                ?: Result.failure(Exception("Order not found"))
        }
    }

    override suspend fun getMyOrders(): Result<OrderStateSummary> {
        return if (shouldFail) {
            Result.failure(Exception("Network error"))
        } else {
            val delivered = orders.filter { it.status == "delivered" }
            val pending = orders.filter { it.status == "pending" }
            val canceled = orders.filter { it.status == "canceled" }
            Result.success(OrderStateSummary(delivered, pending, canceled))
        }
    }

    override suspend fun placeOrder(
        total: Int,
        subTotal: Int,
        deliveryFee: Int,
        deliveryAddressName: String,
        latitude: Double,
        longitude: Double,
        paymentType: String,
        cartProductItems: List<CartProductItem>
    ): Result<Order> {
        return if (shouldFail) {
            Result.failure(Exception("Network error"))
        } else {
            val order = Order(
                id = UUID.randomUUID().toString(),
                storeName = "Test Store",
                date = "2025-03-31",
                total = total,
                totalItems = cartProductItems.sumOf { it.quantity },
                status = "pending",
                subTotal = subTotal,
                deliveryFee = deliveryFee,
                deliveryAddress = deliveryAddressName,
                paymentType = paymentType,
                orderItems = cartProductItems.map {
                    OrderItem(
                        image = it.imageUrl,
                        color = it.color,
                        size = it.color,
                        quantity = it.quantity,
                        price = it.price
                    )
                },
            )
            orders.add(order)
            Result.success(order)
        }
    }
}

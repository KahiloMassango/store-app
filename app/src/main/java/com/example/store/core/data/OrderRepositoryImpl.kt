package com.example.store.core.data

import com.example.store.core.data.repository.OrderRepository
import com.example.store.core.model.cart.CartProductItem
import com.example.store.core.model.order.Order
import com.example.store.core.model.order.OrderStateSummary
import com.example.store.core.network.datasources.OrderNetworkDatasource
import com.example.store.core.network.model.order.request.OrderDtoReq
import com.example.store.core.network.model.order.request.OrderItemDtoReq
import com.example.store.core.network.model.order.response.asExternalModel

class OrderRepositoryImpl(
    private val remoteDatasource: OrderNetworkDatasource
): OrderRepository {

    override suspend fun getOrderById(id: String): Result<Order> {
       return remoteDatasource.getOrderById(id).mapCatching { it.asExternalModel() }
    }

    override suspend fun getMyOrders(): Result<OrderStateSummary> {
       return remoteDatasource.getAllOrders().mapCatching { orders ->
           OrderStateSummary(
               delivered = orders.delivered.map { it.asExternalModel() },
               pending = orders.pending.map { it.asExternalModel() },
               canceled = orders.canceled.map { it.asExternalModel() }
           )
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
        val request = OrderDtoReq(
            subTotal = subTotal,
            deliveryFee = deliveryFee,
            total = total,
            deliveryAddressName = deliveryAddressName,
            latitude = latitude,
            longitude = longitude,
            paymentType = paymentType,
            orderItems = cartProductItems.map {
                OrderItemDtoReq(
                    productItemId = it.id,
                    quantity = it.quantity
                )
            }
        )
       return remoteDatasource.placeOrder(request).mapCatching { it.asExternalModel() }
    }
}
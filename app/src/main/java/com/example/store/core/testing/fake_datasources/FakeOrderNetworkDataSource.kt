package com.example.store.core.testing.fake_datasources

import com.example.store.core.network.datasources.OrderNetworkDatasource
import com.example.store.core.network.model.order.request.OrderDtoReq
import com.example.store.core.network.model.order.response.OrderDtoRes
import com.example.store.core.network.model.order.response.OrdersDtoRes
import com.example.store.core.testing.fake_data.fakeOrderDtoRes1

class FakeOrderNetworkDataSource : OrderNetworkDatasource {

    private var forceNetworkError = false
    private val orders = mutableListOf<OrderDtoRes>()

    fun setForceNetworkError(value: Boolean) {
        forceNetworkError = value
    }

    fun addFakeOrder(order: OrderDtoRes) {
        orders.add(order)
    }

    override suspend fun placeOrder(request: OrderDtoReq): Result<OrderDtoRes> {
        return if (forceNetworkError) {
            Result.failure(Exception("Network error"))
        } else {
            Result.success(fakeOrderDtoRes1)
        }
    }

    override suspend fun getOrderById(id: String): Result<OrderDtoRes> {

       return when {
            forceNetworkError ->  Result.failure(Exception("Network error"))
            orders.any { it.id == id } -> Result.success(fakeOrderDtoRes1)
            else -> Result.failure(Exception("Order not found"))
        }
    }

    override suspend fun getAllOrders(): Result<OrdersDtoRes> {
        return if (forceNetworkError) {
            Result.failure(Exception("Network error"))
        } else {
            Result.success(
                OrdersDtoRes(
                    delivered = listOf(fakeOrderDtoRes1),
                    pending = listOf(fakeOrderDtoRes1),
                    canceled = listOf(fakeOrderDtoRes1)
                )
            )
        }

    }
}
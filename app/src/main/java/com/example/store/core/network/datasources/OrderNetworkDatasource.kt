package com.example.store.core.network.datasources

import com.example.store.core.network.model.order.request.OrderDtoReq
import com.example.store.core.network.model.order.response.OrderDtoRes
import com.example.store.core.network.model.order.response.OrdersDtoRes

interface OrderNetworkDatasource {

    suspend fun placeOrder(request: OrderDtoReq): Result<OrderDtoRes>

    suspend fun getOrderById(id: String): Result<OrderDtoRes>

    suspend fun getAllOrders(): Result<OrdersDtoRes>

}
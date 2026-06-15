package com.example.store.core.network

import com.example.store.core.network.common.extractErrorMessage
import com.example.store.core.network.datasources.OrderNetworkDatasource
import com.example.store.core.network.model.order.request.OrderDtoReq
import com.example.store.core.network.model.order.response.OrderDtoRes
import com.example.store.core.network.model.order.response.OrdersDtoRes
import com.example.store.core.network.retrofit.AuthenticatedApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class OrderNetworkDatasourceImpl(
    private val apiService: AuthenticatedApiService
) : OrderNetworkDatasource {

    override suspend fun placeOrder(request: OrderDtoReq): Result<OrderDtoRes> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.placeOrder(request)
                Result.success(response.data)
            } catch (e: HttpException) {
                val message = extractErrorMessage(e)
                Result.failure(Exception(message))
            } catch (e: IOException) {
                Result.failure(IOException("Verifique sua conexão com a internet"))
            }
        }
    }

    override suspend fun getOrderById(id: String): Result<OrderDtoRes> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getOrderById(id)
                Result.success(response.data)
            } catch (e: HttpException) {
                val message = extractErrorMessage(e)
                Result.failure(Exception(message))
            } catch (e: IOException) {
                Result.failure(IOException("Verifique sua conexão com a internet"))
            }
        }
    }

    override suspend fun getAllOrders(): Result<OrdersDtoRes> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getAllOrders()
                Result.success(response.data)
            } catch (e: HttpException) {
                val message = extractErrorMessage(e)
                Result.failure(Exception(message))
            } catch (e: IOException) {
                Result.failure(IOException("Verifique sua conexão com a internet"))
            }
        }
    }
}
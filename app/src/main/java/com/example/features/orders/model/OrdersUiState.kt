package com.example.features.orders.model

import com.example.store.core.model.order.OrderStateSummary

internal sealed class OrdersUiState {
    data object Loading : OrdersUiState()
    data class Success(val summary: OrderStateSummary) : OrdersUiState()
    data class Error(val message: String) : OrdersUiState()

}
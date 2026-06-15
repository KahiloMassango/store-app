package com.example.features.orders

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.features.orders.model.OrdersUiState
import com.example.store.core.data.repository.OrderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
internal class MyOrdersViewModel @Inject constructor(
    private val orderRepository: OrderRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<OrdersUiState> = MutableStateFlow(OrdersUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        loadOrders()
    }

    fun loadOrders() {
        viewModelScope.launch {
            orderRepository.getMyOrders()
                .onSuccess {
                    _uiState.value = OrdersUiState.Success(it)
                }
                .onFailure {
                    _uiState.value = OrdersUiState.Error(it.message ?: "Network error")
                }
        }
    }

}



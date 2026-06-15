package com.example.store.features.userprofile.orderdeail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.store.core.data.repository.OrderRepository
import com.example.store.core.model.order.Order
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class OrderDetailViewModel @Inject constructor(
    private val orderRepository: OrderRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val orderId = savedStateHandle.get<String>("orderId") ?: ""

    private var _uiState = MutableStateFlow<OrderDetailUiState>(OrderDetailUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        loadOrder()
    }

    fun loadOrder() {
        viewModelScope.launch {
            orderRepository.getOrderById(orderId)
                .onSuccess {
                    _uiState.value = OrderDetailUiState.Success(it)
                }
                .onFailure {
                    it.printStackTrace()
                    _uiState.value = OrderDetailUiState.Error
                }
        }
    }
}


internal sealed class OrderDetailUiState {
    data object Loading : OrderDetailUiState()
    data object Error : OrderDetailUiState()
    data class Success(val order: Order) : OrderDetailUiState()
}

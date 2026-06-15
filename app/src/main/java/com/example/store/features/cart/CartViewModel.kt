package com.example.store.features.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.store.core.data.repository.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartRepository: CartRepository,
) : ViewModel() {

    val uiState = cartRepository.getCartProductsStream()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    val cartTotal = cartRepository.getCartTotalStream()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = 0
        )

    fun removeProductFromCart(productId: String) {
        viewModelScope.launch {
            cartRepository.removeCartProduct(productId)
        }
    }

    fun updateQuantity(productId: String, quantity: Int) {
        viewModelScope.launch {
            if (quantity < 0) {
                return@launch
            }
            cartRepository.updateQuantity(productId, quantity)
        }
    }
}

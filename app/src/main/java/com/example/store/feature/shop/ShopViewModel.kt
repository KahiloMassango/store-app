package com.example.store.feature.shop

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.store.core.data.repository.ProductRepository
import com.example.store.core.model.product.Product
import com.example.store.feature.shop.model.OrderCriteria
import com.example.store.feature.shop.model.getFilters
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ShopViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val gender = savedStateHandle.getStateFlow("gender", "")
    val category = savedStateHandle.getStateFlow("category", "")

    private val _uiState = MutableStateFlow<ShopUiState>(ShopUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        getProducts()
    }

    fun getProducts() {
        _uiState.value = ShopUiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            productRepository.getProducts(gender.value, category.value)
                .onSuccess { products ->
                    _uiState.value = ShopUiState.Success(products)
                }
                .onFailure {
                    _uiState.value = ShopUiState.Error
                }
        }

    }

    fun updateOrderOption(orderBy: String) {
        _uiState.update { currentState ->
            (currentState as ShopUiState.Success).copy(orderBy = orderBy)
        }
    }


}

sealed class ShopUiState {
    data object Loading : ShopUiState()
    data class Success(
        val products: List<Product>,
        val orderBy: String = OrderCriteria.Popular.title,
        val filter: String = ""
    ) : ShopUiState()

    data object Error : ShopUiState()

}
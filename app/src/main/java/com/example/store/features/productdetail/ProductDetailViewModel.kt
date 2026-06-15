package com.example.store.features.productdetail

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.store.core.data.repository.CartRepository
import com.example.store.core.data.repository.ProductRepository
import com.example.store.core.model.product.ProductItem
import com.example.store.core.model.product.ProductWithVariation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ProductDetailViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    private val cartRepository: CartRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val productId = savedStateHandle.get<String>("productId") ?: ""

    private val _selectedColor = MutableStateFlow<String?>(null)
    val selectedColor: StateFlow<String?> = _selectedColor

    private val _selectedSize = MutableStateFlow<String?>(null)
    val selectedSize: StateFlow<String?> = _selectedSize

    private val _filteredColors = MutableStateFlow<List<String>>(emptyList())
    val filteredColors: StateFlow<List<String>> = _filteredColors

    private val _filteredSizes = MutableStateFlow<List<String>>(emptyList())
    val filteredSizes: StateFlow<List<String>> = _filteredSizes

    private val _selectedItem = MutableStateFlow<ProductItem?>(null)
    val selectedItem: StateFlow<ProductItem?> = _selectedItem

    val productImages = mutableStateOf<List<String>>(emptyList())

    private val _uiState: MutableStateFlow<ProductDetailState> =
        MutableStateFlow(ProductDetailState.Loading)
    var uiState = _uiState.asStateFlow()

    init {
        loadProduct()
    }


    fun loadProduct() {
        viewModelScope.launch {
            productRepository.getProductById(productId)
                .onSuccess { product ->
                    _uiState.value = ProductDetailState.Success(product)
                    initAttributes(product)
                }
                .onFailure {
                    Log.e("ProductDetailViewModel", "Error fetching product", it)
                    _uiState.value = ProductDetailState.Error
                }
        }
    }


    private fun initAttributes(product: ProductWithVariation) {
        productImages.value = listOf(product.image) + product.productItems.mapNotNull { it.image }
        if (product.category.hasColorVariation) {
            _filteredColors.value = product.productItems.mapNotNull { it.color }.distinct()
            selectColor(_filteredColors.value.firstOrNull())

        } else if (product.category.hasSizeVariation)
            _filteredSizes.value = product.productItems.mapNotNull { it.size }.distinct()
        selectSize(_filteredSizes.value.firstOrNull())
    }


    fun selectColor(color: String?) {
        _selectedColor.value = color
        val productItems = (uiState.value as ProductDetailState.Success).product.productItems

        // Update sizes based on the selected color
        val sizesForColor = productItems
            .filter { it.color == color }
            .mapNotNull { it.size }
            .distinct()
        _filteredSizes.value = sizesForColor

        // Automatically select the first available size
        _selectedSize.value = sizesForColor.firstOrNull()

        updateSelectedItem(productItems)
    }

    fun selectSize(size: String?) {
        _selectedSize.value = size
        val productItems = (uiState.value as ProductDetailState.Success).product.productItems
        updateSelectedItem(productItems)
    }

    private fun updateSelectedItem(productItems: List<ProductItem>) {
        _selectedItem.value = productItems.firstOrNull {
            it.color == _selectedColor.value && it.size == _selectedSize.value
        }
    }



    fun refresh() {
        loadProduct()
    }


    fun addCart() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val product = (uiState.value as ProductDetailState.Success).product
                val productItem = _selectedItem.value!!
                cartRepository.addToCart(
                    productName = product.name,
                    productId = product.id,
                    imageUrl = product.image,
                    productItem = productItem
                )
            } catch (e: Exception) {
                Log.d("ProductDetailViewModel", "addToCart: $e")
            }

        }
    }
}


internal sealed class ProductDetailState {
    data object Loading : ProductDetailState()
    data object Error : ProductDetailState()
    data class Success(val product: ProductWithVariation) : ProductDetailState()
}
package com.example.store.features.store.model

import com.example.store.core.model.store.Store

internal sealed class StoreUiState {
    data object Loading : StoreUiState()
    data class Success(val store: Store) : StoreUiState()
    data class Error(val message: String) : StoreUiState()
}

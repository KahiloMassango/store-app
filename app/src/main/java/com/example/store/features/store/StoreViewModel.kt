package com.example.store.features.store

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.store.core.data.repository.StoreRepository
import com.example.store.features.store.model.StoreUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class StoreViewModel @Inject constructor(
    private val storeRepository: StoreRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val storeId = savedStateHandle.getStateFlow("id", "")

    private val _uiState = MutableStateFlow<StoreUiState>(StoreUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        loadStore()
    }


    fun loadStore() {
        viewModelScope.launch {
            storeRepository.getStoreDetailById(storeId.value)
                .onSuccess {
                    _uiState.value = StoreUiState.Success(it)
                }
                .onFailure { ex ->
                    _uiState.value = StoreUiState.Error(ex.message ?: "Unknown error")

                }
        }
    }

}



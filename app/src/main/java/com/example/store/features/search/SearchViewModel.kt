package com.example.store.features.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.store.core.data.repository.ProductRepository
import com.example.store.core.data.repository.RecentSearchRepository
import com.example.store.core.model.product.Product
import com.example.store.core.ui.util.hotFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class SearchViewModel @Inject constructor(
    private val recentSearchRepository: RecentSearchRepository,
    private val productRepository: ProductRepository
) : ViewModel() {

    var searchQuery by mutableStateOf("")
        private set


    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val searchResults: StateFlow<List<Product>> = snapshotFlow { searchQuery }
        .debounce(200)
        .mapLatest {
            productRepository.searchProducts(it).getOrElse { emptyList() }
        }.hotFlow(
            scope = viewModelScope,
            initialValue = emptyList()
        )

    val recentSearchQueries = recentSearchRepository.getRecentSearches()
        .hotFlow(
            viewModelScope,
            emptyList()
        )

    fun saveSearchQuery() {
        viewModelScope.launch(Dispatchers.IO) {
            recentSearchRepository.saveSearch(searchQuery)
        }
    }

    fun deleteRecentSearchById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            recentSearchRepository.deleteRecentSearchById(id)
        }
    }

    fun clearAllRecentSearches() {
        viewModelScope.launch(Dispatchers.IO) {
            recentSearchRepository.clearAllRecentSearches()
        }
    }

    fun updateSearchQuery(query: String) {
        searchQuery = query
    }

}
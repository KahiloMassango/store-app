package com.example.store.features.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.store.core.model.product.Product
import com.example.store.core.model.search.RecentSearch
import com.example.store.core.ui.component.LargeProductCard
import com.example.store.core.ui.component.SearchField
import com.example.store.core.ui.component.mockProduct
import com.example.store.core.ui.theme.StoreTheme
import com.example.store.core.ui.util.PhonePreviews
import com.example.store.features.search.components.RecentSearchQueries

@Composable
internal fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel(),
    onProductClick: (String) -> Unit,
    onNavigateUp: () -> Unit,
) {

    val searchResult by viewModel.searchResults.collectAsStateWithLifecycle()
    val recentSearchQueries by viewModel.recentSearchQueries.collectAsStateWithLifecycle()
    val searchQuery = viewModel.searchQuery


    SearchContent(
        modifier = modifier,
        searchQuery = searchQuery,
        onQueryChange = viewModel::updateSearchQuery,
        searchResult = searchResult,
        onSearch = { viewModel.saveSearchQuery() },
        onProductClick = onProductClick,
        onNavigateBack = onNavigateUp,
        recentSearchList = recentSearchQueries,
        onClearAllRecentSearch = viewModel::clearAllRecentSearches,
        onDeleteRecentSearch = viewModel::deleteRecentSearchById,
    )

}

@Composable
private fun SearchContent(
    modifier: Modifier = Modifier,
    searchQuery: String,
    onQueryChange: (String) -> Unit,
    onSearch: () -> Unit,
    searchResult: List<Product>,
    recentSearchList: List<RecentSearch>,
    onClearAllRecentSearch: () -> Unit,
    onDeleteRecentSearch: (Int) -> Unit,
    onProductClick: (String) -> Unit,
    onNavigateBack: () -> Unit
) {
    val focusRequester = remember { FocusRequester() }
    var hasRequestedFocus by rememberSaveable { mutableStateOf(false) }
    val keyboardManager = LocalSoftwareKeyboardController.current
    val focusController = LocalFocusManager.current

    LaunchedEffect(Unit) {
        if(!hasRequestedFocus) {
            focusRequester.requestFocus()
            hasRequestedFocus = true
        }
    }

    Surface(
        modifier = modifier
            .statusBarsPadding()
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        focusController.clearFocus()
                        keyboardManager?.hide()
                    }
                )
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Row(
                modifier = Modifier.padding(top = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    modifier = Modifier
                        .size(24.dp)
                        .clickable(
                            interactionSource = null,
                            indication = null
                        ) { onNavigateBack() },
                    imageVector = Icons.AutoMirrored.Default.ArrowBackIos,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSecondaryContainer
                )
                SearchField(
                    modifier = Modifier.focusRequester(focusRequester),
                    query = searchQuery,
                    placeholder = "Pesquise por lojas, roupas, calçados, acessórios, etc.",
                    onQueryChange = { onQueryChange(it) },
                    onSearch = {
                        focusController.clearFocus()
                        keyboardManager?.hide()
                        onSearch()
                    },
                    onClearQuery = { onQueryChange("") },
                )
            }
            when {
                searchQuery.isEmpty() -> {
                    RecentSearchQueries(
                        modifier = Modifier.fillMaxWidth(),
                        recentSearchList = recentSearchList,
                        onRecentSearchClick = {
                            onQueryChange(it)
                            focusController.clearFocus()
                            keyboardManager?.hide()
                        },
                        onClearAllRecentSearch = onClearAllRecentSearch,
                        onDeleteRecentSearch = { id -> onDeleteRecentSearch(id) },
                    )
                }

                searchResult.isNotEmpty() -> {
                    LazyColumn(
                        modifier = modifier
                            .weight(1f),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        contentPadding = PaddingValues(4.dp)
                    ) {
                        items(searchResult) { product ->
                            LargeProductCard(
                                product = product,
                                onClick = { onProductClick(it) }
                            )
                        }
                    }
                }
                else -> EmptySearchResult()
            }
        }
    }
}


@Composable
private fun EmptySearchResult(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Nenhum resultado Encontrado 🙂",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.inverseOnSurface
        )
    }
}


@PhonePreviews
@Composable
private fun Preview() {
    StoreTheme {
        SearchContent(
            searchQuery = "Calça",
            onQueryChange = {},
            onSearch = {  },
            searchResult = listOf(
                mockProduct.copy(name = "This is a very large long prodocut name you see"),
                mockProduct,
                mockProduct
            ),
            recentSearchList = emptyList(),
            onClearAllRecentSearch = {},
            onDeleteRecentSearch = {},
            onProductClick = {},
            onNavigateBack = {}
        ) 
    }
}
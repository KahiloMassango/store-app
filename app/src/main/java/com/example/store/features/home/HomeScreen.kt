package com.example.store.features.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.store.core.model.product.Product
import com.example.store.core.ui.ErrorScreen
import com.example.store.core.ui.component.SearchField
import com.example.store.core.ui.component.mockProduct
import com.example.store.core.ui.theme.StoreTheme
import com.example.store.core.ui.util.PhonePreviews
import com.example.store.features.home.component.HomeBanner
import com.example.store.features.home.component.HomeLoadingContent
import com.example.store.features.home.component.Section

@Composable
internal fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    onProductClick: (String) -> Unit,
    onSeeAll: (String) -> Unit,
    onSearch: () -> Unit,
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
    when (uiState) {
        is HomeUiState.Error -> ErrorScreen(onTryAgain = viewModel::loadProducts)
        is HomeUiState.Loading -> HomeLoadingContent()
        is HomeUiState.Success -> HomeContent(
            modifier = modifier,
            onProductClick = onProductClick,
            onSeeMore = onSeeAll,
            onSearch = onSearch,
            products = uiState.products
        )
    }

}

@Composable
private fun HomeContent(
    modifier: Modifier = Modifier,
    onProductClick: (String) -> Unit,
    onSeeMore: (String) -> Unit,
    onSearch: () -> Unit,
    products: List<Product>
) {
    Surface(
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(32.dp))
            SearchField(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .clickable(null, null, onClick = onSearch),
                query = "",
                placeholder = "Pesquise por lojas, roupas, calçados, acessórios, etc.",
                enabled = false,
                onQueryChange = {},
                onSearch = {},
                onClearQuery = {},
            )
            Spacer(modifier = Modifier.height(18.dp))
            HomeBanner()
            Spacer(modifier = Modifier.height(18.dp))
            Column(
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(18.dp)
            ) {
                Section(
                    title = "Recomendandos",
                    description = "Dê uma olhada 👀",
                    products = products,
                    onProductClick = { onProductClick(it) },
                    onSeeMore = { onSeeMore(it) },
                )

                Section(
                    title = "Novos",
                    description = "Você nunca viu isso antes!",
                    products = products,
                    onProductClick = { onProductClick(it) },
                    onSeeMore = { onSeeMore(it) },
                )
            }
            Spacer(modifier = Modifier.height(18.dp))
        }
    }
}



//@PreviewLightDark
@PhonePreviews
@Composable
private fun Preview() {
    StoreTheme {
        HomeContent(
            products = listOf(
                mockProduct.copy(name = "Short product name", storeName = "Short store name"),
                mockProduct,
                mockProduct
            ),
            onProductClick = {},
            onSeeMore = {},
            onSearch = {}
        )
    }
}


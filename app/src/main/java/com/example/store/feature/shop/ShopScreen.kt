package com.example.store.feature.shop

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.store.core.model.product.Product
import com.example.store.core.ui.ErrorScreen
import com.example.store.core.ui.component.StoreCenteredTopBar
import com.example.store.core.ui.component.mockProduct
import com.example.store.core.ui.theme.StoreTheme
import com.example.store.core.ui.util.PhonePreviews
import com.example.store.feature.shop.component.FilterContainer
import com.example.store.feature.shop.component.ProductsGrid
import com.example.store.feature.shop.component.ShopLoadingScreen
import com.example.store.feature.shop.model.Filter
import com.example.store.feature.shop.model.Gender


@Composable
internal fun ShopScreen(
    viewModel: ShopViewModel = hiltViewModel(),
    gender: String,
    onProductClick: (String) -> Unit,
    onNavigateUp: () -> Unit
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
    val filters = emptyList<Filter>()

    Scaffold(
        topBar = {
            StoreCenteredTopBar(
                title = Gender.entries.find { it.description == gender }!!.description,
                canNavigateBack = true,
                onNavigateUp = onNavigateUp
            )
        },
        contentWindowInsets = WindowInsets.navigationBars.exclude(BottomAppBarDefaults.windowInsets)
    ) { paddingValues ->
        AnimatedContent(
            modifier = Modifier.padding(paddingValues),
            targetState = uiState,
            transitionSpec = {
                fadeIn(animationSpec = tween(300))
                    .togetherWith(fadeOut(animationSpec = tween(300)))
            },
            label = "ShopScreenContentAnimation"
        ) { state ->
            when (state) {
                is ShopUiState.Error -> {
                    ErrorScreen(onTryAgain = { /* Retry logic */ })
                }

                is ShopUiState.Loading -> {
                    ShopLoadingScreen()
                }

                is ShopUiState.Success -> {
                    ShopContent(
                        currentFilter = state.filter,
                        orderBy = state.orderBy,
                        products = state.products,
                        filters = filters,
                        onFilterChange = {  },
                        onProductClick = { onProductClick(it) },
                        onChangeOrderOption = { viewModel.updateOrderOption(it) },
                    )
                }
            }
        }
    }
}


@Composable
private fun ShopContent(
    modifier: Modifier = Modifier,
    currentFilter: String,
    orderBy: String,
    products: List<Product>,
    filters: List<Filter>,
    onFilterChange: (String) -> Unit,
    onProductClick: (String) -> Unit,
    onChangeOrderOption: (String) -> Unit,
) {

    Surface(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            FilterContainer(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                filters = filters,
                currentFilter = currentFilter,
                onSelectFilter = {
                    onFilterChange(it)
                }
            )
            ProductsGrid(
                modifier = Modifier,
                selectedOption = orderBy,
                onChangeOrderOption = { onChangeOrderOption(it) },
                onProductClick = { onProductClick(it) },
                products = products,
            )

        }
    }
}

@PhonePreviews
@Composable
private fun Preview() {
    StoreTheme {
        ShopContent(
            currentFilter = "TODO()",
            orderBy = "TODO()",
            products = listOf(mockProduct, mockProduct, mockProduct, mockProduct),
            filters = listOf(),
            onFilterChange = {},
            onProductClick = {},
            onChangeOrderOption = {}

        )
    }
}


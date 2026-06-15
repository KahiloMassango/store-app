package com.example.store.features.store

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.store.core.model.store.Store
import com.example.store.core.ui.ErrorScreen
import com.example.store.core.ui.LoadingScreen
import com.example.store.core.ui.component.StoreCenteredTopBar
import com.example.store.core.ui.theme.StoreTheme
import com.example.store.features.store.components.StoreDetailSection
import com.example.store.features.store.components.StoreMapDialog
import com.example.store.features.store.components.StoreProductsGrid
import com.example.store.features.store.components.StoreSection
import com.example.store.features.store.components.StoreSectionTabRow
import com.example.store.features.store.components.StoreSmallDetailCard
import com.example.store.features.store.model.StoreUiState


@Composable
internal fun StoreScreen(
    modifier: Modifier = Modifier,
    viewmodel: StoreViewModel = hiltViewModel(),
    onProductClick: (String) -> Unit,
    onNavigateUp: () -> Unit
) {

    val uiState = viewmodel.uiState.collectAsStateWithLifecycle().value

    when (uiState) {
        is StoreUiState.Loading -> { LoadingScreen() }
        is StoreUiState.Error -> ErrorScreen(onTryAgain = viewmodel::loadStore)
        is StoreUiState.Success -> StoreContent(
            modifier = modifier,
            store = uiState.store,
            onProductClick = onProductClick,
            onNavigateUp = onNavigateUp
        )
    }
}

@Composable
private fun StoreContent(
    modifier: Modifier = Modifier,
    store: Store,
    onProductClick: (String) -> Unit,
    onNavigateUp: () -> Unit
) {
    var currentSection by rememberSaveable { mutableStateOf(StoreSection.Products) }
    var showMapDialog by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        modifier = modifier,
        topBar = {
            StoreCenteredTopBar(
                title = "Loja",
                onNavigateUp = onNavigateUp,
                elevation = 5.dp
            )
        }

    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                    .fillMaxSize()
            ) {
                StoreSmallDetailCard(
                    modifier = Modifier.fillMaxWidth(),
                    storeName = store.name,
                    storeLogo = store.logoUrl.replace("localhost", "10.0.2.2"),
                    totalProducts = store.totalProducts,
                    totalOrders = store.totalCompletedOrders
                )

                StoreSectionTabRow(
                    modifier = Modifier.fillMaxWidth(),
                    currentSection = currentSection,
                    onChangeSection = { currentSection = it }
                )


                Spacer(Modifier.height(16.dp))

                AnimatedContent(
                    targetState = currentSection,
                    modifier = Modifier.weight(1f), label = ""
                ) { section ->
                    when (section) {
                        StoreSection.Products -> {
                            StoreProductsGrid(
                                products = store.products,
                                onProductClick = onProductClick
                            )
                        }
                        StoreSection.Detail -> {
                            StoreDetailSection(
                                store = store,
                                onSeeOnMapClick = { showMapDialog = true }
                            )
                        }
                    }
                }
            }
        }
    }
    AnimatedVisibility(
        enter = fadeIn(),
        exit = fadeOut(),
        visible = showMapDialog
    ) {
        StoreMapDialog(
            modifier = Modifier,
            store = store,
            onDismiss = { showMapDialog = false }
        )

    }
}


@Preview
@Composable
private fun Preview() {
    StoreTheme {
        StoreScreen(
            onProductClick = {},
            onNavigateUp = { }
        )
    }
}
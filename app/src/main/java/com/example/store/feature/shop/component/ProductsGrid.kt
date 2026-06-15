package com.example.store.feature.shop.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.store.core.model.product.Product
import com.example.store.core.ui.component.ProductCard
import com.example.store.core.ui.component.ThemePreviews
import com.example.store.core.ui.theme.StoreTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductsGrid(
    modifier: Modifier = Modifier,
    products: List<Product>,
    onProductClick: (String) -> Unit,
    selectedOption: String,
    onChangeOrderOption: (String) -> Unit

) {
    var isSortingOptionOpen by remember { mutableStateOf(false) }
    val bottomSheetState = rememberModalBottomSheetState()
    val coroutineScope = rememberCoroutineScope()

    if (isSortingOptionOpen) {
        OrderOptionsBottomSheet(
            state = bottomSheetState,
            currentOrderOption = selectedOption,
            onChangeOrderOption = {
                onChangeOrderOption(it)
                coroutineScope.launch {
                    delay(200)
                    bottomSheetState.hide()
                    //delay(100)
                }.invokeOnCompletion { isSortingOptionOpen = false }
            },
            onDismissRequest = { isSortingOptionOpen = false }
        )
    }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        SortingHeader(
            modifier = Modifier
                .zIndex(1f),
            onSortClick = { isSortingOptionOpen = true }
        )
        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            columns = GridCells.Adaptive(150.dp),
            verticalArrangement = Arrangement.spacedBy(22.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(products) { product ->
                ProductCard(
                    modifier = Modifier,
                    product = product,
                    onClick = { onProductClick(it) },
                )
            }
        }
    }
}


@ThemePreviews
@Composable
private fun Preview() {
    StoreTheme {
        //ProductCardSkeleton()
    }
}
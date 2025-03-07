package com.example.store.features.userprofile.orderdeail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.store.core.ui.component.StoreCenteredTopBar
import com.example.store.core.ui.theme.StoreTheme
import com.example.store.features.userprofile.orderdeail.components.OrderInformation
import com.example.store.features.userprofile.orderdeail.components.OrderProductCard
import com.example.store.features.userprofile.orderdeail.components.OrderSummary

@Composable
internal fun OrderDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: OrderDetailViewModel = hiltViewModel(),
    onNavigateUp: () -> Unit
) {

    Scaffold(
        modifier = modifier,
        topBar = {
            StoreCenteredTopBar(
                title = "Detalhes do Pedido",
                canNavigateBack = true,
                onNavigateUp = onNavigateUp,
                elevation = 5.dp
            )
        }
    ) { contentPadding ->
        Surface(
            modifier = Modifier.padding(contentPadding)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(18.dp)
            ) {
                item {
                    OrderInformation(
                        orderNumber = "1947034",
                        date = "05-12-2019",
                        itemsQty = 3,
                        status = "Entregue"
                    )
                }


                items(7) {
                    OrderProductCard()
                }

                item { OrderSummary() }

            }
        }
    }
}


@Preview
@Composable
private fun Preview() {
    StoreTheme {
        OrderDetailScreen(
            onNavigateUp = {}
        )
    }
}
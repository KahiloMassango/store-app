package com.example.store.features.userprofile.orderdeail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.store.core.model.order.Order
import com.example.store.core.ui.ErrorScreen
import com.example.store.core.ui.LoadingScreen
import com.example.store.core.ui.component.StoreCenteredTopBar
import com.example.store.core.ui.theme.StoreTheme
import com.example.store.core.ui.util.toCurrency
import com.example.store.features.userprofile.orderdeail.components.OrderInformation
import com.example.store.features.userprofile.orderdeail.components.OrderItemCard
import com.example.store.features.userprofile.orderdeail.components.OrderSummary

@Composable
internal fun OrderDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: OrderDetailViewModel = hiltViewModel(),
    onNavigateUp: () -> Unit
) {

    val uiState = viewModel.uiState.collectAsState().value

    when (uiState) {
        is OrderDetailUiState.Error -> ErrorScreen(onTryAgain = viewModel::loadOrder)
        is OrderDetailUiState.Loading -> LoadingScreen()
        is OrderDetailUiState.Success -> {
            OrderDetailContent(
                modifier = modifier,
                order = uiState.order,
                onNavigateUp = onNavigateUp
            )
        }
    }


}

@Composable
internal fun OrderDetailContent(
    modifier: Modifier = Modifier,
    order: Order,
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
                        storeName = order.storeName,
                        date = order.date,
                        itemsQty = order.orderItems.size,
                        status = order.status
                    )
                }

                items(order.orderItems) { orderItem ->
                    OrderItemCard(
                        orderItem = orderItem
                    )
                }

                item {
                    OrderSummary(
                        deliveryAddress = order.deliveryAddress,
                        paymentType = order.paymentType,
                        total = order.total.toCurrency()
                    )
                }

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
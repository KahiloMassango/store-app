package com.example.features.orders

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.features.orders.component.MyOrdersTabs
import com.example.features.orders.component.OrdersContentList
import com.example.features.orders.model.OrderTabs
import com.example.features.orders.model.OrdersUiState
import com.example.store.core.model.order.Order
import com.example.store.core.model.order.OrderStateSummary
import com.example.store.core.ui.ErrorScreen
import com.example.store.core.ui.LoadingScreen
import com.example.store.core.ui.component.StoreLargeTopBar
import com.example.store.core.ui.theme.StoreTheme
import com.example.store.core.ui.util.PhonePreviews


@Composable
internal fun MyOrdersScreen(
    viewmodel: MyOrdersViewModel = hiltViewModel(),
    onDetailClick: (String) -> Unit,
    onNavigateUp: () -> Unit,
) {
    val uiState = viewmodel.uiState.collectAsState().value

    when (uiState) {
        is OrdersUiState.Loading -> LoadingScreen()
        is OrdersUiState.Error -> ErrorScreen(onTryAgain = viewmodel::loadOrders)
        is OrdersUiState.Success -> MyOrdersContent(
            orderSummary = uiState.summary,
            onDetailClick = onDetailClick,
            onNavigateUp = onNavigateUp
        )
    }
}

@Composable
internal fun MyOrdersContent(
    modifier: Modifier = Modifier,
    onDetailClick: (String) -> Unit,
    onNavigateUp: () -> Unit,
    orderSummary: OrderStateSummary
) {
    var currentTab by rememberSaveable { mutableStateOf(OrderTabs.DELIVERED) }
    Scaffold(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            StoreLargeTopBar(
                title = "Minhas Encomendas",
                canNavigateBack = true,
                onNavigateUp = onNavigateUp
            )
        },
        contentWindowInsets = WindowInsets.navigationBars.exclude(BottomAppBarDefaults.windowInsets)
    ) { paddingValues ->
        Surface(
            modifier = Modifier.padding(paddingValues),
        ) {
            Column(
                modifier = Modifier
                    .padding()
                    .fillMaxSize(),
            ) {
                Spacer(modifier = Modifier.height(14.dp))
                Box(
                    modifier = modifier
                        .fillMaxWidth()
                        .clip(GenericShape { size, _ ->
                            lineTo(size.width, 0f)
                            lineTo(size.width, Float.MAX_VALUE)
                            lineTo(0f, Float.MAX_VALUE)
                        })
                        .shadow(4.dp)
                        .background(MaterialTheme.colorScheme.surface),

                    ) {
                    MyOrdersTabs(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                        currentTab = currentTab,
                        onSelectTab = { currentTab = it },
                    )
                }
                AnimatedContent(
                    targetState = currentTab, label = "AnimatedContent"
                ) { tab ->
                    when (tab) {
                        OrderTabs.DELIVERED -> OrdersContentList(
                            orders = orderSummary.delivered,
                            onDetailClick = onDetailClick
                        )

                        OrderTabs.PROCESSING -> OrdersContentList(
                            orders = orderSummary.pending,
                            onDetailClick = onDetailClick
                        )

                        OrderTabs.CANCELED -> OrdersContentList(
                            orders = orderSummary.canceled,
                            onDetailClick = onDetailClick
                        )
                    }
                }
            }
        }
    }
}


@PhonePreviews
@Composable
private fun Preview() {
    StoreTheme {
        MyOrdersContent(
            onDetailClick = {},
            onNavigateUp = {},
            orderSummary = OrderStateSummary(
                delivered = listOf(mockOrder, mockOrder),
                pending = listOf(mockOrder, mockOrder),
                canceled = listOf(mockOrder)
            )
        )
    }
}

val mockOrder = Order(
    id = "TODO()",
    storeName = "A very long store name can you see it",
    date = "16-03-2025",
    total = 62879,
    totalItems = 7,
    status = "",
    subTotal = 99,
    deliveryFee = 88,
    deliveryAddress = "",
    paymentType = "",
    orderItems = emptyList(),
)
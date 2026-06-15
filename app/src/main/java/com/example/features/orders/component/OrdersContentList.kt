package com.example.features.orders.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.store.core.model.order.Order

@Composable
internal fun OrdersContentList(
    modifier: Modifier = Modifier,
    orders: List<Order>,
    onDetailClick: (String) -> Unit
) {
    LazyColumn(
        modifier = modifier.fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(16.dp)
    ) {
        items(orders) { order ->
            OrderItemCard(
                modifier = Modifier.padding(bottom = 24.dp),
                order = order,
                onDetailClick = onDetailClick
            )
        }
    }
}

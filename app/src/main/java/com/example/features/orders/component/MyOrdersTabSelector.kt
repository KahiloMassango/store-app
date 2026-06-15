package com.example.features.orders.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.example.features.orders.model.OrderTabs
import com.example.store.core.ui.theme.StoreTheme


@Composable
internal fun MyOrdersTabs(
    modifier: Modifier = Modifier,
    currentTab: OrderTabs,
    onSelectTab: (OrderTabs) -> Unit,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        OrderTabs.entries.forEach { tab ->
            MyOrdersTabItem(
                text = tab.tabTitle,
                isSelected = currentTab == tab,
                onClick = { onSelectTab(tab) }
            )
        }
    }
}


@Composable
private fun MyOrdersTabItem(
    modifier: Modifier = Modifier,
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor by animateColorAsState(
        targetValue = if (isSelected) MaterialTheme.colorScheme.secondary
        else Color.Transparent,
        label = ""
    )
    val textColor by animateColorAsState(
        targetValue = if (isSelected) MaterialTheme.colorScheme.onSecondary
        else MaterialTheme.colorScheme.onSurface,
        label = ""
    )

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(50))
            .background(color = backgroundColor)
            .clickable { onClick() }
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = text,
            style = MaterialTheme.typography.bodySmall,
            color = textColor
        )
    }
}


@PreviewLightDark
@Composable
private fun Preview() {
    var currentTab by remember { mutableStateOf(OrderTabs.DELIVERED) }

    StoreTheme {
        MyOrdersTabs(
            currentTab = currentTab,
            onSelectTab = { currentTab = it },
        )
    }
}


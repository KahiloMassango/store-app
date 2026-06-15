package com.example.store.feature.shop.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.store.core.ui.component.CustomDragHandle
import com.example.store.feature.shop.model.OrderCriteria


@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun OrderOptionsBottomSheet(
    state: SheetState,
    currentOrderOption: String,
    onDismissRequest: () -> Unit,
    onChangeOrderOption: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    ModalBottomSheet(
        modifier = modifier,
        sheetState = state,
        containerColor = MaterialTheme.colorScheme.surface.copy(0.99f),
        scrimColor = Color(0xFF000000).copy(0.3f),
        onDismissRequest = onDismissRequest,
        dragHandle = {
            CustomDragHandle("Ordenar por")
        }
    ) {
        Column(
            modifier = Modifier
                .navigationBarsPadding()
                .fillMaxWidth()
                .padding(bottom = 4.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OrderCriteria.entries.forEach { option ->
                OrderOptionItem(
                    selected = option.title == currentOrderOption,
                    option = option,
                    onClick = { onChangeOrderOption(option.title) }
                )
            }
        }
    }
}


@Composable
private fun OrderOptionItem(
    modifier: Modifier = Modifier,
    selected: Boolean,
    option: OrderCriteria,
    onClick: () -> Unit,
    ) {
    Box(
        modifier = modifier
            .background(
                if (selected) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.surface
            )
            .fillMaxWidth()
            .clickable { onClick() },
    ) {
        Text(
            modifier = Modifier.padding(16.dp),
            text = option.description,
            style = MaterialTheme.typography.bodyMedium,
            color = if (selected) MaterialTheme.colorScheme.onPrimary
            else MaterialTheme.colorScheme.onSurface
        )
    }
}
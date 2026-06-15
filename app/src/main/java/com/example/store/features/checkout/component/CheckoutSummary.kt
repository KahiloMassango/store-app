package com.example.store.features.checkout.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.store.core.ui.util.toCurrency

@Composable
internal fun CheckoutSummary(
    modifier: Modifier = Modifier,
    cartTotal: Int,
    deliveryPrice: Int,
    totalSummary: Int,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        SummaryItem(description = "Carrinho", value = cartTotal.toCurrency())
        SummaryItem(description = "Entrega", value = deliveryPrice.toCurrency())
        SummaryItem(description = "Total", value = totalSummary.toCurrency())

    }
}

@Composable
private fun SummaryItem(
    modifier: Modifier = Modifier,
    description: String,
    value: String,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "$description:",
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.inverseOnSurface,
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurface,
        )
    }
}

package com.example.store.features.checkout.component


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.example.store.core.ui.theme.StoreTheme
import com.example.store.core.ui.util.toCurrency

@Composable
internal fun DeliveryFeeSection(
    modifier: Modifier = Modifier,
    deliveryPrice: Int?,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(
            modifier = modifier,
            text = "Taxa de Entrega",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface,
        )
        DeliveryCard(
            price = deliveryPrice
        )
    }
}


@Composable
private fun DeliveryCard(
    modifier: Modifier = Modifier,
    price: Int?,
) {
    OutlinedCard(
        modifier = modifier,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
        colors = CardDefaults.outlinedCardColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            Text(
                text = "Entrega",
                style = MaterialTheme.typography.bodyMedium,
            )
            Text(
                text = price?.toCurrency() ?: "0.0",
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.SemiBold,
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun Preview() {
    StoreTheme {
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            DeliveryCard(
                price = 1500
            )

        }
    }
}


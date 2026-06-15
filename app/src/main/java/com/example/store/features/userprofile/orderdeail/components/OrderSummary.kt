package com.example.store.features.userprofile.orderdeail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
internal fun OrderSummary(
    modifier: Modifier = Modifier,
    deliveryAddress: String,
    paymentType: String,
    total: String
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        Text(
            text = "Informações do Pedido",
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium
        )
        OrderDescription(text = "Endereço de Entrega", description = deliveryAddress)
        OrderDescription(text = "Método de pagamento", description = paymentType)
        OrderDescription(text = "Total", description = total)
    }
}
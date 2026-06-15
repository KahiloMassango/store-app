package com.example.store.features.userprofile.orderdeail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.store.core.model.order.OrderItem
import com.example.store.core.ui.component.AttributeDescription
import com.example.store.core.ui.theme.StoreTheme
import com.example.store.core.ui.util.toCurrency

@Composable
internal fun OrderItemCard(
    modifier: Modifier = Modifier,
    orderItem: OrderItem
) {
    Card(
        modifier = modifier
            .height(104.dp),
        elevation = CardDefaults.elevatedCardElevation(5.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onBackground,
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            AsyncImage(
                model = orderItem.image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(104.dp)
            )
            Column(
                modifier = Modifier.padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(3.dp)
            ) {
                Text(
                    text = "T-Shirt SPANISH",
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Medium
                )
/*                Text(
                    text = "Mango",
                    color = MaterialTheme.colorScheme.inverseOnSurface,
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Light
                )*/
                Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                    orderItem.color?.let {
                        AttributeDescription(attribute = "Cor", value = it)
                    }
                    orderItem.size?.let {
                        AttributeDescription(attribute = "Tamanho", value = it)
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    AttributeDescription(attribute = "Qtd", value = orderItem.quantity.toString())
                    Text(
                        text = orderItem.price.toCurrency(),
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun Preview() {
    StoreTheme {
     //   OrderItemCard()
    }
}
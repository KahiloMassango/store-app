package com.example.store.features.cart.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
internal fun QuantitySelector(
    modifier: Modifier = Modifier,
    quantity: Int,
    stockQuantity: Int,
    onDecrease: () -> Unit,
    onIncrease: () -> Unit
) {
    Row(
        modifier = modifier
            .width(85.dp),
        verticalAlignment = CenterVertically,
    ) {
        Icon(
            modifier = Modifier
                .size(26.dp)
                .clickable(null, null) { onDecrease() },
            imageVector = Icons.Default.Remove,
            contentDescription = null,
        )

        Text(
            text = quantity.toString(),
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1f)

        )

        Icon(
            modifier = Modifier
                .size(26.dp)
                .clickable(null, null) {
                    if (quantity < stockQuantity) {
                        onIncrease()
                    }
                },
            imageVector = Icons.Default.Add,
            contentDescription = null,
        )
    }
}

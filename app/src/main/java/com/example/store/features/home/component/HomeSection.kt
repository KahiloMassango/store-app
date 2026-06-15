package com.example.store.features.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.example.store.core.model.product.Product
import com.example.store.core.ui.component.ProductCard
import com.example.store.core.ui.component.mockProduct
import com.example.store.core.ui.theme.StoreTheme

@Composable
fun Section(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    products: List<Product>,
    onProductClick: (String) -> Unit,
    onSeeMore: (String) -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = description,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.inverseOnSurface,
        )
        Spacer(modifier = Modifier.height(20.dp))
        LazyRow(
            modifier = Modifier,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(products) { product ->
                ProductCard(
                    product = product,
                    onClick = { onProductClick(it) },
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun Preview() {
    StoreTheme {
        Section(
            title = "Sale",
            description = "Super summer sale",
            onProductClick = {},
            onSeeMore = {},
            products = listOf(mockProduct, mockProduct, mockProduct)
        )
    }
}


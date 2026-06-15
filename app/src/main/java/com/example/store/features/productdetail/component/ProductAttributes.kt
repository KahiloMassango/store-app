package com.example.store.features.productdetail.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.store.core.model.Category

@Composable
internal fun ProductAttributes(
    category: Category,
    selectedSize: String?,
    selectedColor: String?,
    onShowSizeOptions: () -> Unit,
    onShowColorOptions: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        when {
            category.hasSizeVariation && category.hasColorVariation -> {
                // Show both Color and Size dropdowns
                Row(
                    modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    Attribute(
                        modifier = Modifier.weight(1f),
                        label = "Cor",
                        placeholder = "Selecione uma cor",
                        selectedOption = selectedColor,
                        onClick = onShowColorOptions
                    )
                    Attribute(
                        modifier = Modifier.weight(1f),
                        label = "Tamanho",
                        placeholder = "Selecione um tamanho",
                        selectedOption = selectedSize,
                        onClick = onShowSizeOptions
                    )
                }
            }

            category.hasColorVariation -> {
                Attribute(
                    modifier = Modifier.fillMaxWidth(0.5f),
                    label = "Cor",
                    placeholder = "Selecione uma cor",
                    selectedOption = selectedColor,
                    onClick = onShowColorOptions
                )
            }

            category.hasSizeVariation -> {
                Attribute(
                    modifier = Modifier.fillMaxWidth(0.5f),
                    label = "Tamanho",
                    placeholder = "Selecione um tamanho",
                    selectedOption = selectedSize,
                    onClick = onShowSizeOptions
                )
            }
        }
    }
}

@Composable
private fun Attribute(
    label: String,
    placeholder: String,
    selectedOption: String?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {

    OutlinedCard(
        modifier = modifier,
        onClick = onClick,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
        colors = CardDefaults.outlinedCardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = selectedOption?.let { "$label: $selectedOption" } ?: placeholder,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = null
            )
        }
    }
}

package com.example.store.features.newaddress.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.store.core.model.AddressType

@Composable
internal fun AddressTypeSelector(
    modifier: Modifier = Modifier,
    selectedAddressType: AddressType,
    onChooseAddressType: (AddressType) -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Text(
            text = "Selecione o tipo de endereço",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Row(
            modifier = Modifier.padding(start = 5.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            AddressType.entries.forEach { addressType ->
                AddressTypeOption(
                    addressType = addressType,
                    selected = selectedAddressType == addressType,
                    onClick = { onChooseAddressType(it) }
                )
            }
        }
    }
}

@Composable
private fun AddressTypeOption(
    modifier: Modifier = Modifier,
    addressType: AddressType,
    selected: Boolean,
    onClick: (AddressType) -> Unit
) {
    val backgroundColor = if(selected) MaterialTheme.colorScheme.primary
    else MaterialTheme.colorScheme.surface
    val borderColor = if(selected) MaterialTheme.colorScheme.primary
    else MaterialTheme.colorScheme.outlineVariant
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(10))
            .background(backgroundColor, RoundedCornerShape(10.dp))
            .border(1.dp, borderColor, RoundedCornerShape(10.dp))
            .clickable { onClick(addressType) }
        ,
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.padding(5.dp),
            text = addressType.description,
            style = MaterialTheme.typography.bodySmall,
            color = if(selected) MaterialTheme.colorScheme.onPrimary
                else   MaterialTheme.colorScheme.onSurface
        )
    }
}
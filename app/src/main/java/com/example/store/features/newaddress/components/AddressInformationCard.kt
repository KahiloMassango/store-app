package com.example.store.features.newaddress.components


import com.example.store.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.example.store.core.model.AddressLine
import com.example.store.core.ui.component.CustomButton
import com.example.store.core.ui.theme.StoreTheme

@Composable
internal fun AddressInformationCard(
    modifier: Modifier = Modifier,
    addressLine: AddressLine,
    onConfirmLocation: () -> Unit = {}
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        elevation = CardDefaults.elevatedCardElevation(12.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                modifier = Modifier.padding(16.dp),
                text = "Marque sua localização",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.inverseOnSurface
            )
            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.2f))
            Column(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {

                AddressDescription(
                    shorAddressName = addressLine.shortName,
                    address = addressLine.address
                )

                CustomButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Confirmar e adicionar detalhes",
                    onClick = onConfirmLocation
                )
            }
        }
    }
}

@Composable
private fun AddressDescription(
    modifier: Modifier = Modifier,
    shorAddressName: String,
    address: String,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(
            modifier = Modifier
                .background(
                    MaterialTheme.colorScheme.inverseOnSurface.copy(alpha = 0.2f),
                    RoundedCornerShape(10.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier
                    .padding(14.dp)
                    .size(26.dp),
                painter = painterResource(id = R.drawable.ic_map_pin),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSecondaryContainer
            )

        }
        Column(
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Text(
                text = shorAddressName,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = address,
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Normal,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}


@PreviewLightDark
@Composable
private fun Preview() {
    StoreTheme {
        AddressInformationCard(
            addressLine = AddressLine(
                "'Maianga', 'Luanda'",
                "56FV+4HV, Luanda",
            )
        )
    }
}
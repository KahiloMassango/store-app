package com.example.store.features.store.components


import com.example.store.R
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.store.core.model.store.Store

@Composable
internal fun StoreDetailSection(
    modifier: Modifier = Modifier,
    store: Store,
    onSeeOnMapClick: () -> Unit,
) {

    Column(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        Text(
            text = "Detalhes",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(Modifier.height(14.dp))
        Text(
            text = store.description,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Justify,
            softWrap = true,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(Modifier.height(22.dp))
        OpeningDetail(
            openingTime = store.openingTime,
            closingTime = store.closingTime
        )
        Spacer(Modifier.height(18.dp))
        DetailItem(
            title = "Membro desde",
            detail = store.memberSince,
            icon = R.drawable.ic_user
        )
        Spacer(Modifier.height(18.dp))
        DetailItem(
            title = "NIF",
            detail = store.nif,
            icon = R.drawable.ic_user_admin
        )
        Spacer(Modifier.height(18.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            AddressDetail(
                modifier = Modifier,
                address = store.address,
            )
            Text(
                modifier = Modifier.clickable(
                    indication = null,
                    interactionSource = null
                ) { onSeeOnMapClick() },
                text = "Ver no mapa",
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}
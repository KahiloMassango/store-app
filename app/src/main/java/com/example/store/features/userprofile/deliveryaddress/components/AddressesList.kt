package com.example.store.features.userprofile.deliveryaddress.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.store.core.model.Address


@Composable
internal fun AddressesList(
    modifier: Modifier = Modifier,
    addressList: List<Address>,
    onDeleteAddress: (Int) -> Unit,
    onViewOnMap: (Address) -> Unit,
) {
    LazyColumn(
        modifier = modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        items(addressList) { address ->
            AddressCard(
                address = address,
                onDeleteAddress = onDeleteAddress,
                onViewOnMap = { onViewOnMap(it) },
            )
        }
    }
}
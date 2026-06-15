package com.example.store.features.deliveryaddress

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.store.core.model.Address
import com.example.store.core.ui.component.StoreCenteredTopBar
import com.example.store.core.ui.theme.StoreTheme
import com.example.store.features.deliveryaddress.components.NewAddressButton
import com.example.store.features.deliveryaddress.components.AddressesList
import com.example.store.features.deliveryaddress.components.MapDialog

@Composable
internal fun DeliveryAddressScreen(
    modifier: Modifier = Modifier,
    viewModel: DeliveryAddressesViewModel = hiltViewModel(),
    onAddNewAddress: () -> Unit,
    onNavigateUp: () -> Unit
) {
    val addressList by viewModel.deliveryAddresses.collectAsStateWithLifecycle()
    var showMapDialog by rememberSaveable { mutableStateOf(false) }
    var selectedAddress: Address? by remember { mutableStateOf(null) }

    Scaffold(
        modifier = modifier,
        topBar = {
            StoreCenteredTopBar(
                title = "Endereços de entrega",
                elevation = 3.dp,
                canNavigateBack = true,
                onNavigateUp = onNavigateUp
            )
        }
    ) { paddingValues ->
        Surface(
            modifier = modifier
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                NewAddressButton(onClick = onAddNewAddress)
                Spacer(modifier = Modifier.height(20.dp))
                AddressesList(
                    addressList = addressList,
                    onDeleteAddress = { viewModel.deleteAddress(it) },
                    onViewOnMap = { address ->
                        selectedAddress = address
                        showMapDialog = true
                    }
                )
            }
        }

    }
    AnimatedVisibility(
        enter = fadeIn(),
        exit = fadeOut(),
        visible = showMapDialog
    )  {
        MapDialog(
            modifier = Modifier,
            address = selectedAddress!!,
            onDismiss = { showMapDialog = false }
        )
    }
}



@Preview
@Composable
private fun Preview() {
    StoreTheme {
        DeliveryAddressScreen(
            onAddNewAddress = {},
            onNavigateUp = {}
        )
    }
}
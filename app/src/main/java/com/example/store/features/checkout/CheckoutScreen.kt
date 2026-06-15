package com.example.store.features.checkout

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.store.core.model.Address
import com.example.store.core.model.AddressLine
import com.example.store.core.model.AddressType
import com.example.store.core.ui.component.CustomButton
import com.example.store.core.ui.component.StoreCenteredTopBar
import com.example.store.core.ui.theme.StoreTheme
import com.example.store.core.ui.util.PhonePreviews
import com.example.store.features.checkout.component.AddressSection
import com.example.store.features.checkout.component.CheckoutSectionText
import com.example.store.features.checkout.component.CheckoutSummary
import com.example.store.features.checkout.component.DeliveryAddressList
import com.example.store.features.checkout.component.DeliveryFeeSection


@Composable
internal fun CheckoutScreen(
    modifier: Modifier = Modifier,
    viewModel: CheckoutViewModel = hiltViewModel(),
    onAddAddress: () -> Unit,
    onNavigateUp: () -> Unit,
) {
    val cartTotal by viewModel.cartTotal.collectAsStateWithLifecycle()
    val orderTotal by viewModel.orderTotal.collectAsStateWithLifecycle()
    val deliveryFee by viewModel.deliveryFee.collectAsStateWithLifecycle()
    val currentDeliveryAddress by viewModel.currentDeliveryAddress.collectAsStateWithLifecycle()
    val deliveryAddresses by viewModel.deliveryAddresses.collectAsStateWithLifecycle()

    CheckoutContent(
        modifier = modifier,
        deliveryAddress = currentDeliveryAddress,
        deliveryAddresses = deliveryAddresses,
        deliveryPrice = deliveryFee,
        cartTotal = cartTotal,
        orderTotal = orderTotal,
        onNavigateUp = onNavigateUp,
        onAddAddress = onAddAddress,
        onChangeDeliveryAddress = { viewModel.changeDeliveryAddress(it) },
        onCheckout = viewModel::processOrder
    )


}

@Composable
private fun CheckoutContent(
    modifier: Modifier = Modifier,
    onNavigateUp: () -> Unit,
    deliveryAddresses: List<Address>,
    deliveryAddress: Address?,
    deliveryPrice: Int?,
    onChangeDeliveryAddress: (Address) -> Unit,
    onAddAddress: () -> Unit,
    cartTotal: Int,
    orderTotal: Int,
    onCheckout: () -> Unit
) {
    val sectionSpacing = 38.dp
    var changeDeliveryAddress by rememberSaveable { mutableStateOf(false) }
    Scaffold(
        modifier = modifier,
        topBar = {
            StoreCenteredTopBar(
                title = "Finalizar Compra",
                canNavigateBack = true,
                onNavigateUp = onNavigateUp,
                elevation = 5.dp
            )
        },
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
            ) {
                AddressSection(
                    address = deliveryAddress,
                    onChangeAddress = { changeDeliveryAddress = true }
                )

                Spacer(modifier = Modifier.height(sectionSpacing))

                DeliveryFeeSection(
                    deliveryPrice = deliveryPrice,
                )

                Spacer(modifier = Modifier.height(sectionSpacing))

                CheckoutSectionText(text = "Forma de pagamento")

                Spacer(modifier = Modifier.weight(1f))

                CheckoutSummary(
                    cartTotal = cartTotal,
                    deliveryPrice = deliveryPrice ?: 0,
                    totalSummary = orderTotal,
                )

                Spacer(modifier = Modifier.height(30.dp))

                CustomButton(
                    modifier = Modifier.fillMaxWidth(),
                    enabled = deliveryPrice != null,
                    text = "Finalizar Compra",
                    onClick = onCheckout,
                )
            }
        }
    }
    AnimatedVisibility(
        enter = fadeIn(),
        exit = fadeOut(),
        visible = changeDeliveryAddress
    ) {

        DeliveryAddressList(
            addresses = deliveryAddresses,
            onAddNewAddress = onAddAddress,
            onSelectAddress = {
                onChangeDeliveryAddress(it)
                changeDeliveryAddress = false
            },
            onDismiss = { changeDeliveryAddress = false }
        )
    }
}

@PhonePreviews
@Composable
private fun Preview() {
    StoreTheme {
        CheckoutContent(
            onNavigateUp = {},
            deliveryAddresses = emptyList(),
            deliveryAddress = Address(
                id = 1,
                receiverName = "Kahilo Pedro Massango",
                phoneNumber = "928323341",
                addressType = AddressType.WORK,
                addressLine = AddressLine("Gamek", "Morro Bento, Luanda"),
                latitude = -8.9324324,
                longitude = 13.543534
            ),
            deliveryPrice = 1800,
            onChangeDeliveryAddress = {},
            onAddAddress = {},
            cartTotal = 654526,
            orderTotal = 3432,
            onCheckout = {}
        )
    }
}

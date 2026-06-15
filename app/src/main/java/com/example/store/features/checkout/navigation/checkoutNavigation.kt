package com.example.store.features.checkout.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.store.features.checkout.CheckoutScreen
import kotlinx.serialization.Serializable


@Serializable
data object CheckoutRoute

fun NavController.navigateToCheckout() = navigate(CheckoutRoute)

fun NavGraphBuilder.checkoutScreen(
    onAddAddress: () -> Unit,
    onNavigateUp: () -> Unit,
) {
    composable<CheckoutRoute> {
        CheckoutScreen(
            onAddAddress = onAddAddress,
            onNavigateUp = onNavigateUp
        )
    }
}


package com.example.store.features.newaddress.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.store.features.newaddress.NewAddressScreen
import kotlinx.serialization.Serializable

@Serializable
object NewAddressRoute

fun NavController.navigateToNewAddressScreen() = navigate(NewAddressRoute)

fun NavGraphBuilder.newAddressScreen(
    onNavigateUp: () -> Unit,
) {
    composable<NewAddressRoute> {
        NewAddressScreen(
            onNavigateUp = onNavigateUp
        )
    }
}

package com.example.store.features.userprofile.policyprivacy.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.store.features.userprofile.policyprivacy.PolicePrivacyScreen
import kotlinx.serialization.Serializable


@Serializable
internal data object PolicePrivacyRoute

fun NavController.navigateToPolicePrivacy() = navigate(PolicePrivacyRoute)

fun NavGraphBuilder.policePrivacyScreen(
    onNavigateUp: () -> Unit
) {
    composable<PolicePrivacyRoute> {
        PolicePrivacyScreen(
            onNavigationUp = onNavigateUp
        )
    }

}
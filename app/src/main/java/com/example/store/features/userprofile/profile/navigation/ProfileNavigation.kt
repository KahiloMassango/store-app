package com.example.store.features.userprofile.profile.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.store.features.userprofile.profile.ProfileScreen
import kotlinx.serialization.Serializable


@Serializable
data object ProfileRoute

fun NavGraphBuilder.profileScreen(
    onMyOrdersClick: () -> Unit,
    onEditProfileClick: () -> Unit,
    onChangePasswordClick: () -> Unit,
    onHelpCenterClick: () -> Unit,
    onAddressesClick: () -> Unit,
    onPolicePrivacyClick: () -> Unit
) {
    composable<ProfileRoute> {
        ProfileScreen(
            onMyOrdersClick = onMyOrdersClick,
            onEditProfileClick = onEditProfileClick,
            onChangePasswordClick = onChangePasswordClick,
            onHelpCenterClick = onHelpCenterClick,
            onPolicePrivacyClick = onPolicePrivacyClick,
            onAddressesClick = onAddressesClick
        )
    }
}
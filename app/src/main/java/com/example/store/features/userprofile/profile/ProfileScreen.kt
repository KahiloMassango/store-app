package com.example.store.features.userprofile.profile

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.store.core.ui.component.ThemePreviews
import com.example.store.core.ui.theme.StoreTheme

@Composable
internal fun ProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: AccountViewModel = hiltViewModel(),
    onMyOrdersClick: () -> Unit,
    onEditProfileClick: () -> Unit,
    onChangePasswordClick: () -> Unit,
    onHelpCenterClick: () -> Unit,
    onAddressesClick: () -> Unit,
    onPolicePrivacyClick: () -> Unit
) {
    val username = viewModel.username
    val email = viewModel.email

    ProfileContent(
        modifier = modifier,
        username = username,
        email = email,
        onMyOrdersClick = onMyOrdersClick,
        onEditProfileClick = onEditProfileClick,
        onChangePasswordClick = onChangePasswordClick,
        onHelpCenterClick = onHelpCenterClick,
        onAddressesClick = onAddressesClick,
        onPolicePrivacyClick = onPolicePrivacyClick,
        userPhotoUrl = null,
        onLogoutClick = viewModel::logout
    )
}




@ThemePreviews
@Composable
private fun Preview() {
    StoreTheme {
        ProfileScreen(
            onMyOrdersClick = {},
            onEditProfileClick = {},
            onChangePasswordClick = {},
            onHelpCenterClick = {},
            onAddressesClick = {},
            onPolicePrivacyClick = {}
        )
    }
}

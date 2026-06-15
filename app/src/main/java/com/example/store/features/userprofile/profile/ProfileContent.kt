package com.example.store.features.userprofile.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.store.core.ui.component.StoreLargeTopBar
import com.example.store.core.ui.theme.StoreTheme
import com.example.store.core.ui.util.PhonePreviews
import com.example.store.features.userprofile.profile.component.ProfileOptions
import com.example.store.features.userprofile.profile.component.UserInfoCard

@Composable
internal fun ProfileContent(
    modifier: Modifier = Modifier,
    username: String,
    email: String,
    onMyOrdersClick: () -> Unit,
    onEditProfileClick: () -> Unit,
    onChangePasswordClick: () -> Unit,
    onHelpCenterClick: () -> Unit,
    onAddressesClick: () -> Unit,
    onPolicePrivacyClick: () -> Unit,
    userPhotoUrl: String?,
    onLogoutClick: () -> Unit
) {
    Scaffold(
        topBar = {
            StoreLargeTopBar(title = "Perfil", canNavigateBack = false)
        },
        contentWindowInsets = WindowInsets.statusBars.exclude(BottomAppBarDefaults.windowInsets)
    ) { paddingValues ->
        Surface(
            modifier = modifier.padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                UserInfoCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    userName = username,//"Matilda Brown",
                    userEmail = email,//"matildabrown@mail.com",
                    userPhotoUrl = userPhotoUrl,
                    onLogout = onLogoutClick
                )
                Spacer(modifier = Modifier.height(46.dp))
                ProfileOptions(
                    modifier = Modifier
                        .weight(1f),
                    onMyOrdersClick = onMyOrdersClick,
                    onEditProfileClick = onEditProfileClick,
                    onChangePasswordClick = onChangePasswordClick,
                    onHelpCenterClick = onHelpCenterClick,
                    onPolicePrivacyClick = onPolicePrivacyClick,
                    onAddressesClick = onAddressesClick
                )
            }
        }
    }
}

@PhonePreviews
@Composable
private fun Preview() {
    StoreTheme {
        ProfileContent(
            username = "Kahilo Pedro Massango",
            email = "example23@gmail.com",
            onMyOrdersClick = {},
            onEditProfileClick = {},
            onChangePasswordClick = {},
            onHelpCenterClick = {},
            onAddressesClick = {},
            onPolicePrivacyClick = {},
            userPhotoUrl = "",
            onLogoutClick = {}
        )
    }
}
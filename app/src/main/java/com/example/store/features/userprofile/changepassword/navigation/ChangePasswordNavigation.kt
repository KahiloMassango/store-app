package com.example.store.features.userprofile.changepassword.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.store.features.userprofile.changepassword.ChangePasswordScreen
import kotlinx.serialization.Serializable

@Serializable
internal data object ChangePasswordRoute

fun NavController.navigateToChangePassword() = navigate(ChangePasswordRoute)

fun NavGraphBuilder.changePasswordScreen(onNavigateUp: () -> Unit, ) {
    composable<ChangePasswordRoute> {
        ChangePasswordScreen(onNavigateUp = onNavigateUp)
    }
}
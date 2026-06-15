package com.example.store.features.userprofile.editprofile.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.store.features.userprofile.editprofile.EditProfileScreen
import kotlinx.serialization.Serializable

@Serializable
internal data object EditProfileRoute


fun NavController.navigateToEditProfile() = navigate(EditProfileRoute)

fun NavGraphBuilder.editProfileScreen(
    onNavigateUp: () -> Unit
) {
    composable<EditProfileRoute>() {
        EditProfileScreen(
            onNavigateUp = onNavigateUp
        )

    }
}
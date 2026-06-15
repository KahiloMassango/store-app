package com.example.store.features.authentication.signup.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.store.features.authentication.signup.SignUpScreen
import kotlinx.serialization.Serializable


@Serializable
data object SignUpRoute

fun NavController.navigateToSignUp() = navigate(SignUpRoute)

fun NavGraphBuilder.signUpScreen(
    onLogin: () -> Unit,
    onNavigateUp: () -> Unit,
) {
    composable<SignUpRoute> {
        SignUpScreen(
            onLogin = onLogin,
            onNavigateUp = onNavigateUp,
        )
    }
}
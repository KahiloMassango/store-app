package com.example.store.features.authentication.login.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.store.features.authentication.login.LoginScreen
import kotlinx.serialization.Serializable


@Serializable
data object LoginRoute

fun NavGraphBuilder.loginScreen(
    onLogin: () -> Unit,
    onSignUp: () -> Unit,
    onForgotPassword: () -> Unit,
    onNavigateUp: () -> Unit,
) {
    composable<LoginRoute> {
        LoginScreen(
            onSignUp = onSignUp,
            onForgotPassword = onForgotPassword,
            onNavigateUp = onNavigateUp,
            onLogin = onLogin
        )
    }
}
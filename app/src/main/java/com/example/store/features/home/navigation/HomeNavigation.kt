package com.example.store.features.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.store.features.home.HomeScreen
import kotlinx.serialization.Serializable


@Serializable
data object HomeRoute

fun NavController.navigateToHome() = navigate(HomeRoute)

fun NavGraphBuilder.homeScreen(
    onProductClick: (String) -> Unit,
    onSeeAll: (String) -> Unit,
    onSearch: () -> Unit
) {
    composable<HomeRoute> {
        HomeScreen (
            onProductClick = { onProductClick(it) },
            onSeeAll = { onSeeAll(it) },
            onSearch = onSearch
        )
    }
}
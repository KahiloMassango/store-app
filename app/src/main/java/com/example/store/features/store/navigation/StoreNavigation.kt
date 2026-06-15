package com.example.store.features.store.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.store.features.store.StoreScreen
import kotlinx.serialization.Serializable

@Serializable
data class StoreRoute(val id: String)

fun NavController.navigateToStore(id: String) = navigate(StoreRoute(id))

fun NavGraphBuilder.storeScreen(
    onProductClick: (String) -> Unit,
    onNavigateUp: () -> Unit
) {
    composable<StoreRoute> {
        StoreScreen(
            onProductClick = onProductClick,
            onNavigateUp = onNavigateUp
        )
    }
}


package com.example.features.orders.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.features.orders.MyOrdersScreen
import kotlinx.serialization.Serializable

@Serializable
data object MyOrdersRoute

fun NavController.navigateToMyOrders() = navigate(MyOrdersRoute)

fun NavGraphBuilder.ordersScreen(
    onOrderClick: (String) -> Unit,
    onNavigateUp: () -> Unit
) {
    composable<MyOrdersRoute> {
        MyOrdersScreen(
            onDetailClick = onOrderClick,
            onNavigateUp = onNavigateUp
        )
    }
}
package com.example.store.features.userprofile.orderdeail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.store.features.userprofile.orderdeail.OrderDetailScreen
import kotlinx.serialization.Serializable


@Serializable
internal data class OrderDetailRoute(val orderId: String)

fun NavController.navigateToOrderDetail(orderId: String) = navigate(OrderDetailRoute(orderId))

fun NavGraphBuilder.orderDetailScreen(
    onNavigateUp: () -> Unit
) {
    composable<OrderDetailRoute>() {
        OrderDetailScreen(
            onNavigateUp = onNavigateUp
        )

    }
}
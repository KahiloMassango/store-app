package com.example.store.features.productdetail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.store.features.productdetail.ProductDetailsScreen
import kotlinx.serialization.Serializable


@Serializable
data class ProductDetailRoute(val productId: String)

fun NavController.navigateToProductDetail(productId: String) = navigate(ProductDetailRoute(productId))

fun NavGraphBuilder.productDetailScreen(
    onReviewsClick: (String) -> Unit,
    onSuggestedProductsClick: (String) -> Unit,
    onStoreClick: (String) -> Unit,
    onNavigateUp: () -> Unit
    ) {
    composable<ProductDetailRoute> {
        ProductDetailsScreen(
            onStoreClick = { onStoreClick(it.toString()) },
            onReviewsClick = { onReviewsClick(it) },
            onNavigateUp = onNavigateUp,
            onSuggestedProductClick = { onSuggestedProductsClick(it) }
        )
    }
}
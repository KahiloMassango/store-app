package com.example.store.features.discover.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.store.features.discover.DiscoverScreen
import kotlinx.serialization.Serializable

@Serializable
object CategoryRoute

fun NavGraphBuilder.categoryScreen(
    onSelectCategory: (category: String, subcategory: String) -> Unit,
    onSearch: () -> Unit
) {
    composable<CategoryRoute> {
        DiscoverScreen(
            onSelectCategory = onSelectCategory,
            onSearch = onSearch
        )
    }
}
package com.example.store.core.testing.fake_repositories

import com.example.store.core.data.repository.CategoryRepository
import com.example.store.core.network.model.sync.CategoryDtoRes
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeCategoryRepository: CategoryRepository {

    private val categories = listOf("clothes", "shoes", "fragances")

    override fun getCategories(): Flow<List<String>> {
        return flowOf(categories)
    }

    override fun getCategoriesByGenderName(name: String): Flow<List<String>> {
        return when(name) {
            "men" -> flowOf(listOf("clothes", "shoes"))
            "women" -> flowOf(listOf("clothes", "fragances"))
            else -> flowOf(emptyList())
        }
    }

    override suspend fun sync(categories: List<CategoryDtoRes>) {
        TODO("Not yet implemented")
    }
}
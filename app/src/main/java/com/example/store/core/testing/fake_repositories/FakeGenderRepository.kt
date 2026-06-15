package com.example.store.core.testing.fake_repositories

import com.example.store.core.data.repository.GenderRepository
import com.example.store.core.network.model.sync.GenderCategoryDtoRes
import com.example.store.core.network.model.sync.GenderDtoRes
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class FakeGenderRepository: GenderRepository {

    private val genders = listOf("men", "women", "kids")

    override fun getGenders(): Flow<List<String>> {
        return flowOf(genders)
    }

    override suspend fun getGendersWithCategories(): Map<String, List<String>> {
        return mapOf(
            "men" to listOf("clothes", "shoes"),
            "women" to listOf("clothes", "fragances"),
            "kids" to listOf("clothes", "shoes")
        )
    }

    override fun getGendersWithCategoriesFlow(): Flow<Map<String, List<String>>> {
        return flowOf(
            mapOf(
                "men" to listOf("clothes", "shoes"),
                "women" to listOf("clothes", "fragances"),
                "kids" to listOf("clothes", "shoes")
            )
        )
    }

    override suspend fun sync(genders: List<GenderDtoRes>) {
        TODO("Not yet implemented")
    }

    override suspend fun syncGenderCategories(genderCategories: List<GenderCategoryDtoRes>) {
        TODO("Not yet implemented")
    }
}
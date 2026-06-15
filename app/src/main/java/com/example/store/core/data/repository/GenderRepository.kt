package com.example.store.core.data.repository

import com.example.store.core.network.model.sync.GenderCategoryDtoRes
import com.example.store.core.network.model.sync.GenderDtoRes
import kotlinx.coroutines.flow.Flow

interface GenderRepository {
    fun getGenders(): Flow<List<String>>

    suspend fun getGendersWithCategories(): Map<String, List<String>>
    fun getGendersWithCategoriesFlow(): Flow<Map<String, List<String>>>
    suspend fun sync(genders: List<GenderDtoRes>)
    suspend fun syncGenderCategories(genderCategories: List<GenderCategoryDtoRes>)
}
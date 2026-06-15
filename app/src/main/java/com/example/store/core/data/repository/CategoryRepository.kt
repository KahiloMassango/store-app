package com.example.store.core.data.repository

import com.example.store.core.network.model.sync.CategoryDtoRes
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    fun getCategories(): Flow<List<String>>

    fun getCategoriesByGenderName(name: String): Flow<List<String>>

    suspend fun sync(categories: List<CategoryDtoRes>)

}
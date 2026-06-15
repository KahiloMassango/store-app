package com.example.seller_app.core.database.datasources

import com.example.store.core.database.model.CategoryEntity
import kotlinx.coroutines.flow.Flow

interface CategoryLocalDataSource {

    suspend fun upsertCategories(categories: List<CategoryEntity>)

    fun getCategoryFlow(): Flow<List<CategoryEntity>>

    fun getCategoriesByGenderName(name: String): Flow<List<CategoryEntity>>

}
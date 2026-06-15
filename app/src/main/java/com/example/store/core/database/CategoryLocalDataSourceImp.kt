package com.example.store.core.database

import com.example.seller_app.core.database.datasources.CategoryLocalDataSource
import com.example.store.core.database.dao.CategoryDao
import com.example.store.core.database.model.CategoryEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class CategoryLocalDataSourceImp(
    private val categoryDao: CategoryDao,
) : CategoryLocalDataSource {

    override suspend fun upsertCategories(categories: List<CategoryEntity>) {
        withContext(Dispatchers.IO) {
            val newCategoriesIds = categories.map { it.categoryId }
            categoryDao.insertCategories(categories)
            categoryDao.deleteCategoriesNotIn(newCategoriesIds)
        }
    }

    override fun getCategoryFlow(): Flow<List<CategoryEntity>> {
        return categoryDao.getCategories()
    }


    override fun getCategoriesByGenderName(name: String): Flow<List<CategoryEntity>> {
        return categoryDao.getCategoriesByGenderName(name).map { it }

    }



}
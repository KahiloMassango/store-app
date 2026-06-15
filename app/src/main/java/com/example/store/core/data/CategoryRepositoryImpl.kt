package com.example.store.core.data

import com.example.seller_app.core.database.datasources.CategoryLocalDataSource
import com.example.store.core.data.model.asEntity
import com.example.store.core.data.repository.CategoryRepository
import com.example.store.core.network.model.sync.CategoryDtoRes
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CategoryRepositoryImpl(
    private val localDataSource: CategoryLocalDataSource,
) : CategoryRepository {

    override fun getCategories(): Flow<List<String>> {
        return localDataSource.getCategoryFlow()
            .map { list -> list.map { it.name } }
    }

    override fun getCategoriesByGenderName(name: String): Flow<List<String>> {
        return localDataSource.getCategoriesByGenderName(name)
            .map { list -> list.map { it.name } }
    }


    override suspend fun sync(categories: List<CategoryDtoRes>) {
        localDataSource.upsertCategories(categories.map { it.asEntity() })
    }

}
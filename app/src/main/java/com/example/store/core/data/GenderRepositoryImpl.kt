package com.example.store.core.data

import com.example.store.core.data.model.asEntity
import com.example.store.core.data.repository.GenderRepository
import com.example.store.core.database.datasources.GenderLocalDataSource
import com.example.store.core.network.model.sync.GenderCategoryDtoRes
import com.example.store.core.network.model.sync.GenderDtoRes
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GenderRepositoryImpl(
    private val localDataSource: GenderLocalDataSource,
) : GenderRepository {

    override fun getGenders(): Flow<List<String>> {
        return localDataSource.getGendersFlow().map { list -> list.map { it.name } }

    }

    override suspend fun getGendersWithCategories(): Map<String, List<String>> {
        val genderWithCategories = localDataSource.getGenderWithCategory()
        return genderWithCategories.associate { genderWithCategory ->
            genderWithCategory.gender.name to genderWithCategory.categories.map { it.name }
        }
    }

    override suspend fun sync(genders: List<GenderDtoRes>) {
        localDataSource.upsertGenders(genders.map { it.asEntity() })
    }

    override suspend fun syncGenderCategories(genderCategories: List<GenderCategoryDtoRes>) {
        localDataSource.saveGenderCategories(genderCategories.map { it.asEntity() })
    }

    override fun getGendersWithCategoriesFlow(): Flow<Map<String, List<String>>> {
        return localDataSource.getGenderWithCategoryFlow().map {
            it.associate { genderWithCategory ->
                genderWithCategory.gender.name to genderWithCategory.categories.map { it.name }
            }
        }
    }

}
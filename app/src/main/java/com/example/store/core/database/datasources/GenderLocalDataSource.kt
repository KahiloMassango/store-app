package com.example.store.core.database.datasources

import com.example.store.core.database.model.GenderCategory
import com.example.store.core.database.model.GenderEntity
import com.example.store.core.database.model.GenderWithCategories
import kotlinx.coroutines.flow.Flow

interface GenderLocalDataSource {
    suspend fun upsertGenders(genders: List<GenderEntity>)
    suspend fun saveGenderCategories(genderCategories: List<GenderCategory>)
    fun getGendersFlow(): Flow<List<GenderEntity>>
    suspend fun getGenderWithCategory(): List<GenderWithCategories>
    fun getGenderWithCategoryFlow(): Flow<List<GenderWithCategories>>

}
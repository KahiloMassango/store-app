package com.example.store.core.testing.fake_datasources

import com.example.store.core.database.datasources.GenderLocalDataSource
import com.example.store.core.database.model.GenderCategory
import com.example.store.core.database.model.GenderEntity
import com.example.store.core.database.model.GenderWithCategories
import com.example.store.core.testing.fake_data.fakeCategoryEntity1
import com.example.store.core.testing.fake_data.fakeCategoryEntity2
import com.example.store.core.testing.fake_data.fakeCategoryEntity3
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeGenderLocalDataSource: GenderLocalDataSource {

    private val fakeGenders = mutableListOf<GenderEntity>()
    private val fakeCategoryEntities = listOf(fakeCategoryEntity1, fakeCategoryEntity2, fakeCategoryEntity3)
    private val fakeGenderCategories = mutableListOf<GenderCategory>()


    override suspend fun upsertGenders(genders: List<GenderEntity>) {
        genders.forEach { newGender ->
            val existingIndex = fakeGenders.indexOfFirst { it.genderId == newGender.genderId }
            if (existingIndex != -1) {
                // Update existing gender
                fakeGenders[existingIndex] = newGender
            } else {
                // Insert new gender
                fakeGenders.add(newGender)
            }
        }
    }

    override suspend fun saveGenderCategories(genderCategories: List<GenderCategory>) {
        fakeGenderCategories.addAll(genderCategories)
    }

    override fun getGendersFlow(): Flow<List<GenderEntity>> {
        return flowOf(fakeGenders)
    }

    override suspend fun getGenderWithCategory(): List<GenderWithCategories> {
        return fakeGenderCategories.map { genderCategory ->
            GenderWithCategories(
                gender = fakeGenders.find { it.genderId == genderCategory.genderId }!!,
                categories = fakeCategoryEntities.filter { it.categoryId == genderCategory.categoryId }
            )
        }
    }

    override fun getGenderWithCategoryFlow(): Flow<List<GenderWithCategories>> {
        return flowOf(fakeGenderCategories.map { genderCategory ->
            GenderWithCategories(
                gender = fakeGenders.find { it.genderId == genderCategory.genderId }!!,
                categories = fakeCategoryEntities.filter { it.categoryId == genderCategory.categoryId }
            )
        })
    }
}
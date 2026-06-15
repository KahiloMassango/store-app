package com.example.store.core.testing.fake_datasources

import com.example.seller_app.core.database.datasources.CategoryLocalDataSource
import com.example.store.core.database.model.CategoryEntity
import com.example.store.core.database.model.GenderCategory
import com.example.store.core.testing.fake_data.fakeCategoryEntity1
import com.example.store.core.testing.fake_data.fakeCategoryEntity2
import com.example.store.core.testing.fake_data.fakeGenderEntity1
import com.example.store.core.testing.fake_data.fakeGenderEntity2
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeCategoryLocalDataSource : CategoryLocalDataSource {
    private val fakeGenders = listOf(fakeGenderEntity1, fakeGenderEntity2)
    private val fakeCategories = mutableListOf<CategoryEntity>()
    private val fakeGenderCategories = listOf(
        GenderCategory(fakeGenderEntity1.genderId, fakeCategoryEntity1.categoryId),
        GenderCategory(fakeGenderEntity2.genderId, fakeCategoryEntity2.categoryId)
    )

    override suspend fun upsertCategories(categories: List<CategoryEntity>) {
        categories.forEach { category ->
            val existingIndex = fakeCategories.indexOfFirst { it.categoryId == category.categoryId }
            if (existingIndex == -1)  {
                fakeCategories.add(category)
            }
            else {
                fakeCategories[existingIndex] = category
            }
        }
    }

    override fun getCategoryFlow(): Flow<List<CategoryEntity>> {
        return flowOf(fakeCategories)
    }

    override fun getCategoriesByGenderName(name: String): Flow<List<CategoryEntity>> {
        val gender = fakeGenders.find { it.name == name } ?: return flowOf(emptyList())
        val genderCategories = fakeGenderCategories.filter { it.genderId == gender.genderId }
        val categories = genderCategories.mapNotNull { genderCategory ->
            fakeCategories.find { it.categoryId == genderCategory.categoryId }
        }
        return flowOf(categories)

    }
}
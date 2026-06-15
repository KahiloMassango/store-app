package com.example.store.core.data

import com.example.store.core.data.repository.CategoryRepository
import com.example.store.core.network.model.sync.CategoryDtoRes
import com.example.store.core.testing.fake_data.fakeCategoryEntity1
import com.example.store.core.testing.fake_data.fakeCategoryEntity2
import com.example.store.core.testing.fake_data.fakeGenderEntity1
import com.example.store.core.testing.fake_datasources.FakeCategoryLocalDataSource
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CategoryRepositoryTest {

    private lateinit var datasource: FakeCategoryLocalDataSource
    private lateinit var repository: CategoryRepository

    @Before
    fun setUp() {
        datasource = FakeCategoryLocalDataSource()
        repository = CategoryRepositoryImpl(datasource)
    }

    @Test
    fun `when getCategories is called, then return all categories`() = runTest {
        // Given
        val expectedCategories = listOf(
            CategoryDtoRes("1", "Men", true, false),
            CategoryDtoRes("2", "Women", false, true),
            CategoryDtoRes("3", "Kids", false, false)
        )
        repository.sync(expectedCategories)

        // When
        val result = repository.getCategories().first()

        // Then
        assertEquals(expectedCategories.map { it.name }, result)
    }

    @Test
    fun `when getCategoriesByGenderName is called with a gender, then return filtered categories`() = runTest {
        // Given
        val categories = listOf(
            CategoryDtoRes(fakeCategoryEntity1.categoryId, fakeCategoryEntity1.name, true, false),
            CategoryDtoRes(fakeCategoryEntity2.categoryId, fakeCategoryEntity2.name, true, false)
        )
        repository.sync(categories)

        // When
        val result = repository.getCategoriesByGenderName(fakeGenderEntity1.name).first()

        // Then
        assertEquals(listOf(fakeCategoryEntity1.name), result)
    }

    @Test
    fun `when sync is called, then categories are updated`() = runTest {
        // Given
        val initialCategories = listOf(
            CategoryDtoRes(fakeCategoryEntity1.categoryId, fakeCategoryEntity1.name, true, false),
            CategoryDtoRes(fakeCategoryEntity2.categoryId, fakeCategoryEntity2.name, true, false)
        )
        repository.sync(initialCategories)

        val updatedCategories = listOf(
            CategoryDtoRes(fakeCategoryEntity1.categoryId, "fake_name 1", true, false),
            CategoryDtoRes(fakeCategoryEntity2.categoryId, "fake_name 2", true, false)
        )

        // When
        repository.sync(updatedCategories)
        val result = repository.getCategories().first()

        // Then
        assertEquals(updatedCategories.map { it.name }, result)
    }
}

package com.example.store.core.data

import com.example.store.core.data.repository.GenderRepository
import com.example.store.core.database.model.GenderCategory
import com.example.store.core.network.model.sync.GenderCategoryDtoRes
import com.example.store.core.network.model.sync.GenderDtoRes
import com.example.store.core.testing.fake_data.fakeCategoryEntity1
import com.example.store.core.testing.fake_data.fakeCategoryEntity2
import com.example.store.core.testing.fake_data.fakeGenderEntity1
import com.example.store.core.testing.fake_data.fakeGenderEntity2
import com.example.store.core.testing.fake_datasources.FakeGenderLocalDataSource
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GenderRepositoryTest {

    private lateinit var dataSource: FakeGenderLocalDataSource
    private lateinit var repository: GenderRepository

    @Before
    fun setup() {
        dataSource = FakeGenderLocalDataSource()
        repository = GenderRepositoryImpl(dataSource)
    }

    @Test
    fun `getGenders returns flow of gender names when not empty`() = runTest {
        val genders = listOf(fakeGenderEntity1, fakeGenderEntity2)
        dataSource.upsertGenders(genders)

        val result = repository.getGenders().first()

        assertEquals(genders.map { it.name }, result)
    }

    @Test
    fun `getGenders returns empty flow of gender names when empty`() = runTest {
        val result = repository.getGenders().first()

        assertTrue(result.isEmpty())
    }

    @Test
    fun `getGendersWithCategories returns mapped data when not empty`() = runTest {
        val genders = listOf(fakeGenderEntity1, fakeGenderEntity2)
        val genderCategory = listOf(
            GenderCategory(fakeGenderEntity1.genderId, fakeCategoryEntity1.categoryId),
            GenderCategory(fakeGenderEntity2.genderId, fakeCategoryEntity2.categoryId)
        )
        dataSource.upsertGenders(genders)
        dataSource.saveGenderCategories(genderCategory)

        val result = repository.getGendersWithCategories()

        assertEquals(
            mapOf(
                fakeGenderEntity1.name to listOf(fakeCategoryEntity1.name),
                fakeGenderEntity2.name to listOf(fakeCategoryEntity2.name)
            ), result
        )

    }

    @Test
    fun `getGendersWithCategories returns empty map local data is empty`() = runTest {
        val result = repository.getGendersWithCategories()

        assertTrue(result.isEmpty())
    }

    @Test
    fun `sync updates genders when already exists`() = runTest {
        val genders = listOf(fakeGenderEntity1)
        dataSource.upsertGenders(genders)

        val updatedGender = GenderDtoRes(fakeGenderEntity1.genderId, "updated name")
        repository.sync(listOf(updatedGender))

        val result = dataSource.getGendersFlow().first()

        assertEquals(updatedGender.name, result.first().name)
    }

    @Test
    fun `sync gender when data provided`() = runTest {
        val genderDtoRes = GenderDtoRes(fakeGenderEntity1.genderId, "gender name")

        repository.sync(listOf(genderDtoRes))

        val result = dataSource.getGendersFlow().first()

        assertTrue(result.isNotEmpty())
        assertEquals(genderDtoRes.id, result.first().genderId)
        assertEquals(genderDtoRes.name, result.first().name)
    }


    @Test
    fun `sync genderCategories when data provided`() = runTest {
        dataSource.upsertGenders(listOf(fakeGenderEntity1))
        val genderCategory = GenderCategoryDtoRes(fakeGenderEntity1.genderId, fakeCategoryEntity1.categoryId)

        repository.syncGenderCategories(listOf(genderCategory))

        val result = dataSource.getGenderWithCategory()

        assertTrue(result.isNotEmpty())
        assertEquals(fakeGenderEntity1.genderId, result.first().gender.genderId)
    }

    @Test
    fun `getGenders emits empty list when local data is empty`() = runTest {
        val result = repository.getGenders().first()
        assertTrue(result.isEmpty())
    }
}
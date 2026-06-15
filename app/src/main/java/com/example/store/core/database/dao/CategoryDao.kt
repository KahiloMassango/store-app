package com.example.store.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.store.core.database.model.CategoryEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface CategoryDao {

    @Query("SELECT * FROM categories")
    fun getCategories(): Flow<List<CategoryEntity>>

    @Query(
        "SELECT c.categoryId, c.name, c.hasSizeVariation, c.hasColorVariation " +
        "FROM categories c " +
                "INNER JOIN GenderCategory gc ON c.categoryId = gc.categoryId " +
                "INNER JOIN genders g ON g.genderId = gc.genderId " +
        "WHERE g.name = :genderName"
    )
    fun getCategoriesByGenderName(genderName: String): Flow<List<CategoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategories(categories: List<CategoryEntity>)

    @Query("DELETE FROM categories WHERE categoryId NOT IN (:ids)")
    suspend fun deleteCategoriesNotIn(ids: List<String>)

}
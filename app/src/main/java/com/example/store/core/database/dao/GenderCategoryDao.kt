package com.example.store.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.store.core.database.model.GenderCategory

@Dao
interface GenderCategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertGenderCategories(genderCategories: List<GenderCategory>)


    @Query("DELETE FROM GenderCategory")
    suspend fun deleteGenderCategories()

}
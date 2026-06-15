package com.example.store.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.store.core.database.model.GenderEntity
import com.example.store.core.database.model.GenderWithCategories
import kotlinx.coroutines.flow.Flow


@Dao
interface GenderDao {

    @Query("SELECT * FROM genders")
    fun getGenderFlow(): Flow<List<GenderEntity>>

    @Transaction
    @Query("SELECT * FROM genders")
    suspend fun getGenderWithCategory(): List<GenderWithCategories>

    @Transaction
    @Query("SELECT * FROM genders")
    fun getGenderWithCategoryFlow(): Flow<List<GenderWithCategories>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenders(genders: List<GenderEntity>)

    @Query("DELETE FROM genders WHERE genderId NOT IN (:ids)")
    suspend fun deleteGendersNotIn(ids: List<String>)

}
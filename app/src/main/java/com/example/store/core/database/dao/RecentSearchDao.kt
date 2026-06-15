package com.example.store.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.store.core.database.model.RecentSearchEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecentSearchDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecentSearch(recentSearch: RecentSearchEntity)

    @Query("SELECT * FROM recent_search ORDER BY id DESC LIMIT 5")
    fun getRecentSearches(): Flow<List<RecentSearchEntity>>

    @Query("DELETE FROM recent_search WHERE id = :id")
    suspend fun deleteRecentSearchById(id: Int)

    @Query("DELETE FROM recent_search")
    suspend fun deleteAllRecentSearches()
}
package com.example.store.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.store.core.database.model.FavoriteProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritesDao {

    @Query("SELECT * FROM favorite_products")
    fun getFavoriteProducts(): Flow<List<FavoriteProductEntity>>

    @Query("SELECT COUNT(*) FROM favorite_products WHERE id = :productId")
    fun getProductCount(productId: String): Flow<Int>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFavoriteProduct(product: FavoriteProductEntity)

    @Query("DELETE FROM favorite_products WHERE id = :productId")
    suspend fun deleteFavoriteProduct(productId: String)

}
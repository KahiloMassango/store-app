package com.example.store.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.store.core.database.model.CartProductItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCartProduct(cartProductItemEntity: CartProductItemEntity)

    @Query("DELETE FROM cart WHERE  id = :id")
    suspend fun deleteCartProduct(id: String)

    @Query("UPDATE cart SET quantity = :quantity WHERE id = :id")
    suspend fun updateQuantity(id: String, quantity: Int)

    @Query("SELECT * FROM cart WHERE id == :id")
    suspend fun getProductByID(id: String): CartProductItemEntity?

    @Query("DELETE FROM cart")
    suspend fun clearCart()

    @Query("SELECT SUM(price * quantity) FROM cart ")
    fun getCartTotalStream(): Flow<Int>

    @Query("SELECT COUNT(*) FROM cart")
    fun getCartProductCount(): Flow<Int>

    @Query("SELECT * FROM cart")
    fun getCartProductsFlow(): Flow<List<CartProductItemEntity>>

    @Query("SELECT * FROM cart")
    fun getCartProducts(): List<CartProductItemEntity>

}
package com.example.store.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.store.core.model.cart.CartProductItem

@Entity(tableName = "cart")
data class CartProductItemEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val productId:String,
    val name: String,
    val price: Int,
    @ColumnInfo(name = "image_url")
    val imageUrl: String,
    val color: String?,
    val size: String?,
    val quantity: Int = 1,
    val stockQuantity: Int
)

fun CartProductItemEntity.asExternalModel() = CartProductItem(
    id = id,
    productId = productId,
    name = name,
    price = price,
    imageUrl = imageUrl,
    color = color,
    size = size,
    quantity = quantity,
    stockQuantity = stockQuantity
)

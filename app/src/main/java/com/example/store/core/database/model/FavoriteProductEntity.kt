package com.example.store.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.store.core.model.FavoriteProduct


@Entity(tableName = "favorite_products")
data class FavoriteProductEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val name: String,
    @ColumnInfo(name = "store_name")
    val storeName: String,
    val price: Int,
    @ColumnInfo(name = "image_url")
    val imageUrl: String,
    val color: String,
    val size: String,
    @ColumnInfo(name = "avg_rating")
    val avgRating: Int,
    @ColumnInfo(name = "total_ratings")
    val totalRatings: Int
)

fun FavoriteProductEntity.asExternalModel() = FavoriteProduct(
    id = id,
    name = name,
    storeName = storeName,
    price = price,
    imageUrl = imageUrl,
    color = color,
    size = size,
    avgRating = avgRating,
    totalRatings = totalRatings
)
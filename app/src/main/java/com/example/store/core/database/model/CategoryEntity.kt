package com.example.store.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.store.core.model.Category

@Entity("categories")
data class CategoryEntity(
    @PrimaryKey(false)
    val categoryId: String,
    @ColumnInfo(name = "name")
    val name: String,
    val hasSizeVariation: Boolean,
    val hasColorVariation: Boolean
)


fun CategoryEntity.asExternalModel() = Category(
    name = name,
    hasColorVariation = hasColorVariation,
    hasSizeVariation = hasSizeVariation
)

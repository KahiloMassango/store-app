package com.example.store.core.database.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation

@Entity(primaryKeys = ["genderId", "categoryId"])
data class GenderCategory(
    val genderId: String,
    val categoryId: String
)

data class GenderWithCategories(
    @Embedded
    val gender: GenderEntity,
    @Relation(
        parentColumn = "genderId",
        entityColumn = "categoryId",
        associateBy = Junction(GenderCategory::class)
    )
    val categories: List<CategoryEntity>
)

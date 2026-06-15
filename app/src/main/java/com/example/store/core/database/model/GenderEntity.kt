package com.example.store.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.store.core.model.Gender

@Entity("genders")
data class GenderEntity(
    @PrimaryKey(false)
    val genderId: String,
    val name: String,
)

fun GenderEntity.asExternalModel(): Gender = Gender(name)
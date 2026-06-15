package com.example.store.core.network.model.sync

import com.example.store.core.model.Category

data class CategoryDtoRes(
    val id: String,
    val name: String,
    val hasSizeVariation: Boolean,
    val hasColorVariation: Boolean
)

fun CategoryDtoRes.asExternalModel() = Category(
    name = name,
    hasSizeVariation = hasSizeVariation,
    hasColorVariation = hasColorVariation
)

package com.example.store.core.data.model

import com.example.store.core.database.model.CategoryEntity
import com.example.store.core.database.model.GenderCategory
import com.example.store.core.database.model.GenderEntity
import com.example.store.core.network.model.sync.CategoryDtoRes
import com.example.store.core.network.model.sync.GenderCategoryDtoRes
import com.example.store.core.network.model.sync.GenderDtoRes

fun GenderCategoryDtoRes.asEntity() = GenderCategory(genderId, categoryId)

fun GenderDtoRes.asEntity() = GenderEntity(
    genderId = id,
    name = name
)

fun CategoryDtoRes.asEntity() = CategoryEntity(
    categoryId = id,
    name =  name,
    hasColorVariation = hasColorVariation,
    hasSizeVariation = hasSizeVariation
)
package com.example.store.core.testing.fake_data

import com.example.store.core.database.model.CategoryEntity

val fakeCategoryEntity1 = CategoryEntity(
    categoryId = "category_1",
    name = "category_name_1",
    hasSizeVariation = true,
    hasColorVariation = true,
)

val fakeCategoryEntity2 = CategoryEntity(
    categoryId = "category_2",
    name = "category_name_2",
    hasSizeVariation = false,
    hasColorVariation = true,
)

val fakeCategoryEntity3 = CategoryEntity(
    categoryId = "category_3",
    name = "category_name_3",
    hasSizeVariation = true ,
    hasColorVariation = false,
)
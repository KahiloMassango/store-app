package com.example.store.core.testing.fake_data.productDtoRes

import com.example.store.core.model.Category
import com.example.store.core.network.model.sync.CategoryDtoRes

val fakeClotheCategoryDtoRes = CategoryDtoRes(
    id = "1",
    name = "fake1",
    hasColorVariation = true,
    hasSizeVariation = true
)

val fakeFraganceCategoryDtoRes = CategoryDtoRes(
    id = "2",
    name = "fake2",
    hasColorVariation = false,
    hasSizeVariation = true
)

val fakeAccessoryCategoryDtoRes = CategoryDtoRes(
    id = "3",
    name = "fake3",
    hasColorVariation = true,
    hasSizeVariation = false
)
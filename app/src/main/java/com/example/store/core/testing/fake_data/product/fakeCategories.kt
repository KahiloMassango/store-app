package com.example.store.core.testing.fake_data.product

import com.example.store.core.model.Category

val fakeClotheCategory = Category(
    name = "fake1",
    hasColorVariation = true,
    hasSizeVariation = true
)

val fakeFraganceCategory = Category(
    name = "fake2",
    hasColorVariation = false,
    hasSizeVariation = true
)

val fakeAccessoryCategory = Category(
    name = "fake3",
    hasColorVariation = true,
    hasSizeVariation = false
)
package com.example.store.core.testing.fake_data.productDtoRes

import com.example.store.core.network.model.product.ProductItemDtoRes

val fakeProductItemDtoRes1 = ProductItemDtoRes(
    id = "1",
    color = "fake color",
    imageUrl = "fake image",
    price = 2200,
    size = "fake size",
    stockQuantity = 6
)

val fakeProductItemDtoRes2 = ProductItemDtoRes(
    id = "1",
    color = "fake color",
    imageUrl = "fake image",
    price = 2200,
    size = null,
    stockQuantity = 1
)

val fakeProductItemDtoRes3 = ProductItemDtoRes(
    id = "1",
    color = null,
    imageUrl = "fake image",
    price = 4000,
    size = "fake size",
    stockQuantity = 4
)


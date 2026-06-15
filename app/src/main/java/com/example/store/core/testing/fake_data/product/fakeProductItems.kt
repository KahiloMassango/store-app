package com.example.store.core.testing.fake_data.product

import com.example.store.core.model.product.ProductItem

val fakeProductItem1 = ProductItem(
    id = "1",
    color = "fake color",
    image = "fake image",
    price = 2200,
    size = "fake size",
    stockQuantity = 6
)

val fakeProductItem2 = ProductItem(
    id = "1",
    color = "fake color",
    image = "fake image",
    price = 2200,
    size = null,
    stockQuantity = 1
)

val fakeProductItem3 = ProductItem(
    id = "1",
    color = null,
    image = "fake image",
    price = 4000,
    size = "fake size",
    stockQuantity = 4
)


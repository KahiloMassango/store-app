package com.example.store.core.testing.fake_data.product

import com.example.store.core.model.product.ProductWithVariation

val fakeProductWithVariation1 = ProductWithVariation(
    id = "1",
    name = "T-Shirt",
    description = "A cool cotton T-Shirt",
    image = "https://example.com/tshirt.jpg",
    storeId = "store_1",
    storeName = "Fashion Store",
    productItems = listOf(fakeProductItem1),
    category = fakeClotheCategory
)

val fakeProductWithVariation2 = ProductWithVariation(
    id = "2",
    name = "Sneakers",
    description = "description",
    image = "https://example.com/sneakers.jpg",
    storeId = "store_2",
    storeName = "Sports Store",
    productItems = listOf(fakeProductItem3),
    category = fakeFraganceCategory
)

val fakeProductWithVariation3 = ProductWithVariation(
    id = "3",
    name = "Casio Watch",
    description = "description",
    image = "https://example.com/watch.jpg",
    storeId = "store_3",
    storeName = "fake store",
    productItems = listOf(fakeProductItem2),
    category = fakeAccessoryCategory
)
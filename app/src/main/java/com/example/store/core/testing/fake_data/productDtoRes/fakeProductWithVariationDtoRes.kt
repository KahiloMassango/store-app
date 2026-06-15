package com.example.store.core.testing.fake_data.productDtoRes

import com.example.store.core.network.model.product.ProductDetailDtoRes

val fakeProductDetailDtoRes1 = ProductDetailDtoRes(
    id = "1",
    name = "T-Shirt",
    description = "A cool cotton T-Shirt",
    imageUrl = "https://example.com/tshirt.jpg",
    storeId = "store_1",
    storeName = "Fashion Store",
    productItems = listOf(fakeProductItemDtoRes1),
    category = fakeClotheCategoryDtoRes
)

val fakeProductDetailDtoRes2 = ProductDetailDtoRes(
    id = "2",
    name = "Sneakers",
    description = "description",
    imageUrl = "https://example.com/sneakers.jpg",
    storeId = "store_2",
    storeName = "Sports Store",
    productItems = listOf(fakeProductItemDtoRes3),
    category = fakeFraganceCategoryDtoRes
)

val fakeProductDetailDtoRes3 = ProductDetailDtoRes(
    id = "3",
    name = "Casio Watch",
    description = "description",
    imageUrl = "https://example.com/watch.jpg",
    storeId = "store_3",
    storeName = "fake store",
    productItems = listOf(fakeProductItemDtoRes2),
    category = fakeAccessoryCategoryDtoRes
)
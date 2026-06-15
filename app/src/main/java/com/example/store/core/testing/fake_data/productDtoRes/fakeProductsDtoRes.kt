package com.example.store.core.testing.fake_data.productDtoRes

import com.example.store.core.network.model.product.ProductDtoRes

val fakeProductDto1 = ProductDtoRes(
    id = "1",
    name = "T-Shirt",
    description = "A cool cotton T-Shirt",
    imageUrl = "https://example.com/tshirt.jpg",
    storeId = "store_1",
    storeName = "Fashion Store",
    minPrice = 2000
)


val fakeProductDto2 = ProductDtoRes(
    id = "2",
    name = "Sneakers",
    description = "Comfortable running sneakers",
    imageUrl = "https://example.com/sneakers.jpg",
    storeId = "store_2",
    storeName = "Sports Store",
    minPrice = 5000
)

val fakeProductDto3 = ProductDtoRes(
    id = "3",
    name = "Jacket",
    description = "Winter jacket",
    imageUrl = "https://example.com    /jacket.jpg",
    storeId = "store_789",
    storeName = "Winter Wear",
    minPrice = 7000
)
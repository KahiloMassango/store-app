package com.example.store.core.testing.fake_data.product

import com.example.store.core.model.product.Product

val fakeProduct1 = Product(
    id = "1",
    name = "T-Shirt",
    description = "A cool cotton T-Shirt",
    image = "https://example.com/tshirt.jpg",
    storeId = "store_1",
    storeName = "Fashion Store",
    minPrice = 2000
)


val fakeProduct2 = Product(
    id = "2",
    name = "Sneakers",
    description = "Comfortable running sneakers",
    image = "https://example.com/sneakers.jpg",
    storeId = "store_2",
    storeName = "Sports Store",
    minPrice = 5000
)

val fakeProduct3 = Product(
    id = "3",
    name = "Jacket",
    description = "Winter jacket",
    image = "https://example.com    /jacket.jpg",
    storeId = "store_789",
    storeName = "Winter Wear",
    minPrice = 7000
)
package com.example.store.core.testing.fake_data

import com.example.store.core.model.store.Store
import com.example.store.core.network.model.StoreDtoRes
import com.example.store.core.testing.fake_data.product.fakeProduct1
import com.example.store.core.testing.fake_data.product.fakeProduct2
import com.example.store.core.testing.fake_data.product.fakeProduct3

val fakeStore1 = Store(
    name = "fake store",
    description = "description",
    logoUrl = "fake url",
    offersDelivery = true,
    nif = "fake",
    memberSince = "memberSince",
    address = "fake",
    latitude = 0.0,
    longitude = 0.0,
    openingTime = "fake",
    closingTime = "fake",
    totalProducts = 5,
    totalCompletedOrders = 9,
    products = listOf(
        fakeProduct1,
        fakeProduct2,
        fakeProduct3
    )
)

val fakeStoreDtoRes1 = StoreDtoRes(
    name = "fake store",
    description = "description",
    logoUrl = "fake url",
    offersDelivery = true,
    nif = "fake",
    memberSince = "memberSince",
    address = "fake",
    latitude = 0.0,
    longitude = 0.0,
    openingTime = "fake",
    closingTime = "fake",
    totalProducts = 5,
    totalCompletedOrders = 9,
    products = emptyList(),
)
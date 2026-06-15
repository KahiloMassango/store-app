package com.example.store.core.testing.fake_data

import com.example.store.core.network.model.order.request.OrderDtoReq
import com.example.store.core.network.model.order.request.OrderItemDtoReq
import com.example.store.core.network.model.order.response.OrderDtoRes
import com.example.store.core.network.model.order.response.OrderItemDtoRes
import com.example.store.core.testing.fake_data.product.fakeProductItem1


val fakeOrderItemDtoRes2 = OrderItemDtoRes(
    image = "image url",
    color = "color",
    size = "size",
    quantity = 2,
    price = 2000
)

val fakeOrderDtoRes1 = OrderDtoRes(
    id = "fake id",
    storeName = "fake store name",
    subTotal = 2400,
    deliveryFee = 1500,
    date = "06/06/2025",
    status = "fake status",
    total = 3900,
    deliveryAddressName = "fake delivery address name",
    paymentType = "fake payment type",
    deliveryMethod = "delivery method",
    orderItems = listOf(fakeOrderItemDtoRes2),
    orderTotalItems = 1
)

val fakeOrderDtoReq1 = OrderDtoReq(
    subTotal = 2400,
    deliveryFee = 1500,
    total = 3900,
    deliveryAddressName = "fake delivery address name",
    latitude = 9.333,
    longitude = 15.555,
    paymentType = "payment",
    orderItems = listOf(
        OrderItemDtoReq(
            productItemId = fakeProductItem1.id,
            quantity = 3
        )
    )
)



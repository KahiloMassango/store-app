package com.example.store.core.testing.fake_data

import com.example.store.core.database.model.AddressEntity
import com.example.store.core.model.Address
import com.example.store.core.model.AddressLine
import com.example.store.core.model.AddressType


val fakeAddress1 = Address(
    id = 1,
    receiverName = "John Doe 1",
    phoneNumber = "123456789",
    addressType = AddressType.HOME,
    addressLine = AddressLine("123 Street", "address"),
    latitude = 5.999,
    longitude = 13.3434,
)

val fakeAddress2 = Address(
    id = 2,
    receiverName = "John Doe 2",
    phoneNumber = "123456789",
    addressType = AddressType.HOME,
    addressLine = AddressLine("123 Street", "address"),
    latitude = 5.999,
    longitude = 13.3434,
)

val fakeAddressEntity1 = AddressEntity(
    id = 1,
    receiverName = "John Doe",
    phoneNumber = "123456789",
    addressType = AddressType.HOME,
    shortName = "Home",
    addressLine = "123 Street",
    latitude = 0.0,
    longitude = 0.0
)

val fakeAddressEntity2 = AddressEntity(
    id = 2,
    receiverName = "Jane Doe",
    phoneNumber = "987654321",
    addressType = AddressType.WORK,
    shortName = "Office",
    addressLine = "456 Avenue",
    latitude = 1.0,
    longitude = 1.0
)
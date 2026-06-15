package com.example.store.core.testing.fake_data.product

import com.example.store.core.model.Address
import com.example.store.core.model.AddressLine
import com.example.store.core.model.AddressType

val fakeAddress1 = Address(
    id = 0,
    receiverName = "address name",
    phoneNumber = "999999999",
    addressType = AddressType.HOME,
    addressLine = AddressLine("short name", "long address name location"),
    latitude = 8.3345,
    longitude = 15.54352
)
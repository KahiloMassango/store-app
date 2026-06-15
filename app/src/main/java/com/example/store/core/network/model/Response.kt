package com.example.store.core.network.model

data class Response<T>(
    val isSuccess: Boolean,
    val message: String,
    val data: T
)
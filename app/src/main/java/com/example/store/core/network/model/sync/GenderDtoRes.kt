package com.example.store.core.network.model.sync

import com.example.store.core.model.Gender

data class GenderDtoRes(
    val id: String,
    val name: String,
)

/*
fun GenderDtoRes.asExternalModel() = Gender(
    name = name
)
*/

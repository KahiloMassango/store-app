package com.example.store.core.network.model.sync


data class SyncMetadataDtoRes(
    val categories: List<CategoryDtoRes>,
    val genders: List<GenderDtoRes>,
    val genderCategoryRelations: List<GenderCategoryDtoRes>,
)

data class GenderCategoryDtoRes(
    val genderId: String,
    val categoryId: String,
)

package com.example.store.core.model

data class Rating(
    val id: String,
    val rate: Int,
    val comment: String?,
    val productId: String,
    val userId: String,
    val ratingDate: String
)

data class RatingInfo(
    val id: String,
    val productId: String,
    val totalRatings: Int,
    val averageRating: Int,
    val ratings: List<Int>
)

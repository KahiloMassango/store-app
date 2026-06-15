package com.example.store.feature.shop.model


import com.example.store.R
import androidx.annotation.DrawableRes


internal enum class Gender(
    val description: String,
    @DrawableRes
    val icon: Int
) {
    Women("Mulher", R.drawable.ic_women_category),
    Men("Homem", R.drawable.ic_man_category),
    Kids("Criança", R.drawable.ic_kids_category),
}

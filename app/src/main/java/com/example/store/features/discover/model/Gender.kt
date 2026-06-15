package com.example.store.features.discover.model


import com.example.store.R
import androidx.annotation.DrawableRes



internal fun getGenderIcon(gender: String): Int {
    return when(gender) {
        "Mulher" -> R.drawable.ic_women_category
        "Homem" -> R.drawable.ic_man_category
        "Criança" -> R.drawable.ic_kids_category
        else -> R.drawable.ic_women_category
    }
}
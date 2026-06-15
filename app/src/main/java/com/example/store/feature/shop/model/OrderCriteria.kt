package com.example.store.feature.shop.model

enum class OrderCriteria(val title: String, val description: String) {
    Popular("popular", "Mais vendidos"),
    Novo("newest", "Mais recentes"),
    PriceAsc("priceLow", "Preço: menor para maior"),
    PriceDesc("priceHigh", "Preço: maior para menor")
}

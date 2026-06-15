package com.example.features.orders.model

internal enum class OrderTabs(
    val tabTitle: String
) {
    DELIVERED("Entregue"),
    PROCESSING( "Processando"),
    CANCELED( "Cancelado")
}

package com.example.store.feature.shop.model


data class Filter(val name: String, val description: String, val category: String)

fun getFilters(section: String, category: String): List<Filter> {
    return when (section) {
        "Women" -> womenFilters.filter { it.category == category }
        "Men" -> menFilters.filter { it.category == category }
        "Kids" -> kidsFilters.filter { it.category == category }
        else -> emptyList()
    }
}


val womenFilters = listOf(
    Filter("Blouses and T-shirts", "Blusas & Camisetas", "Clothes"),
    Filter("Skirt", "Saias", "Clothes"),
    Filter("Dresses","Vestidos", "Clothes"),
    Filter("Blouses","Blusas", "Clothes"),
    Filter("Pants","Calças", "Clothes"),
    Filter("Shirts","Camisas", "Clothes"),
    Filter("Coats","Casacos", "Clothes"),

    Filter("Sandals","Sandálias", "Shoes"),
    Filter("Boots", "Botas", "Shoes"),
    Filter("Slippers", "Chinelos", "Shoes"),
    Filter("Tennis","Tênis", "Shoes"),
    Filter("Sneakers","Sapatilhas", "Shoes")
)

val menFilters = listOf(
    Filter("Blouses","Camisas", "Clothes"),
    Filter("Pants","Calças", "Clothes"),
    Filter("Bermudas", "Bermudas", "Clothes"),
    Filter("T-shirts", "T-shirts", "Clothes"),
    Filter("Coats","Casacos", "Clothes"),

    Filter("Dress Shoes","Sapato Social", "Shoes"),
    Filter("Sneakers","Sapatênis", "Shoes"),
    Filter("Boots", "Botas", "Shoes"),
    Filter("Slippers", "Chinelos", "Shoes"),
    Filter("Sneakers","Sapatênis", "Shoes"),
    Filter("Tennis","Tênis", "Shoes"),
)

val kidsFilters = listOf(
    Filter("Blouses and T-shirts", "Blusas e T-shirts", "Clothes"),
    Filter("Jumpsuits", "Macacões", "Clothes"),
    Filter("Pants","Calças", "Clothes"),
    Filter("Shirts","Camisas", "Clothes"),
    Filter("Pajamas","Pijamas", "Clothes"),
    Filter("Coats","Casacos", "Clothes"),
    Filter("Sets","Conjuntos", "Clothes"),

    Filter("Boots", "Botas", "Shoes"),
    Filter("Slippers", "Chinelos", "Shoes"),
    Filter("Sandals","Sandálias", "Shoes"),
    Filter("Tennis","Tênis", "Shoes"),
    Filter("Sneakers","Sapatilhas", "Shoes")
)



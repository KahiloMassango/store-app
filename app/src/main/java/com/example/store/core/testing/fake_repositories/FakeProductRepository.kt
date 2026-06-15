package com.example.store.core.testing.fake_repositories

import com.example.store.core.data.repository.ProductRepository
import com.example.store.core.model.product.Product
import com.example.store.core.model.product.ProductWithVariation
import com.example.store.core.testing.fake_data.product.fakeProductWithVariation1
import com.example.store.core.testing.fake_data.product.fakeProductWithVariation2
import com.example.store.core.testing.fake_data.product.fakeProductWithVariation3

class FakeProductRepository : ProductRepository {

    private var shouldFail = false

    private var fakeProducts = mutableListOf<Product>()
    private val fakeProductsWithVariations =
        listOf(fakeProductWithVariation1, fakeProductWithVariation2, fakeProductWithVariation3)

    fun setProducts(productsList: List<Product>) {
        fakeProducts.addAll(productsList)
    }

    fun setShouldFail(value: Boolean) {
        shouldFail = value
    }

    override suspend fun getProducts(gender: String?, category: String?): Result<List<Product>> {
        return if (shouldFail) {
            Result.failure(Exception("Network error"))
        } else {
            Result.success(fakeProducts)
        }
    }

    override suspend fun getProductById(id: String): Result<ProductWithVariation> {
        return if (shouldFail) {
            Result.failure(Exception("Network error"))
        } else {
            fakeProductsWithVariations.find { it.id == id }
                ?.let { Result.success(it) }
                ?: Result.failure(Exception("Product not found"))
        }
    }

    override suspend fun searchProducts(query: String): Result<List<Product>> {
        return if (shouldFail) {
            Result.failure(Exception("Search service unavailable"))
        } else {
            Result.success(fakeProducts.filter { it.name.contains(query, ignoreCase = true) })
        }
    }

    override suspend fun getProductsByCategory(category: String): Result<List<Product>> {
        return if (shouldFail) {
            Result.failure(Exception("Category service error"))
        } else {
            Result.success(fakeProducts.filter { it.name.contains(category, ignoreCase = true) })
        }
    }
}

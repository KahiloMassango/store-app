package com.example.store.core.testing.fake_datasources

import com.example.store.core.network.datasources.ProductNetworkDatasource
import com.example.store.core.network.model.product.ProductDtoRes
import com.example.store.core.network.model.product.ProductDetailDtoRes
import com.example.store.core.testing.fake_data.productDtoRes.fakeProductDto1
import com.example.store.core.testing.fake_data.productDtoRes.fakeProductDto2
import com.example.store.core.testing.fake_data.productDtoRes.fakeProductDto3
import com.example.store.core.testing.fake_data.productDtoRes.fakeProductDetailDtoRes1
import com.example.store.core.testing.fake_data.productDtoRes.fakeProductDetailDtoRes2
import java.io.IOException

class FakeProductNetworkDataSource : ProductNetworkDatasource {

    private var shouldFail = false

    private val fakeProducts = listOf(fakeProductDto1, fakeProductDto2, fakeProductDto3)
    private val fakeProductsWithVariations =
        listOf(fakeProductDetailDtoRes1, fakeProductDetailDtoRes2)

    fun setShouldFail(value: Boolean) {
        shouldFail = value
    }


    override suspend fun getProductById(id: String): Result<ProductDetailDtoRes> {
        if (shouldFail) {
            return Result.failure(IOException("Network Error"))
        }
        val product = fakeProductsWithVariations.find { it.id == id }

        return if (product != null) {
            Result.success(product)
        } else {
            Result.failure(Exception("Product not found"))
        }
    }

    override suspend fun searchProducts(query: String): Result<List<ProductDtoRes>> {
        if (shouldFail) {
            return Result.failure(IOException("Network Error"))
        }
        return Result.success(fakeProducts.filter { it.name.contains(query, ignoreCase = true) })
    }

    override suspend fun getProductsByGenderAndCategory(
        gender: String?,
        category: String?
    ): Result<List<ProductDtoRes>> {
        if (shouldFail) {
            return Result.failure(IOException("Category service error"))
        }
        return Result.success(fakeProducts.filter {
            it.name.contains(
                category ?: "non maching",
                ignoreCase = true
            )
        })
    }
}

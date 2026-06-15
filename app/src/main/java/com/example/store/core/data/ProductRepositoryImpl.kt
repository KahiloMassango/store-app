package com.example.store.core.data

import com.example.store.core.data.repository.ProductRepository
import com.example.store.core.model.product.Product
import com.example.store.core.model.product.ProductWithVariation
import com.example.store.core.network.datasources.ProductNetworkDatasource
import com.example.store.core.network.model.product.asExternalModel

class ProductRepositoryImpl(
    private val remoteDatasource: ProductNetworkDatasource
) : ProductRepository {

    override suspend fun getProducts(gender: String?, category: String?): Result<List<Product>> {
        return remoteDatasource.getProductsByGenderAndCategory(gender, category)
            .mapCatching { products ->
                products.map { it.asExternalModel() }
            }
    }

    override suspend fun getProductsByCategory(category: String): Result<List<Product>> {
        return remoteDatasource.getProductsByGenderAndCategory(null, category)
            .mapCatching { products ->
                products.map { it.asExternalModel() }
            }
    }

    override suspend fun getProductById(id: String): Result<ProductWithVariation> {
        return remoteDatasource.getProductById(id).map { it.asExternalModel() }
    }

    override suspend fun searchProducts(query: String): Result<List<Product>> {
        return remoteDatasource.searchProducts(query).mapCatching { products ->
            products.map { it.asExternalModel() }
        }
    }
}
package com.example.store.core.data

import com.example.store.core.data.repository.FavoriteRepository
import com.example.store.core.database.dao.FavoritesDao
import com.example.store.core.database.model.asExternalModel
import com.example.store.core.model.FavoriteProduct
import com.example.store.core.model.product.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

internal class DefaultFavoriteRepository(
    private val favoritesDao: FavoritesDao
): FavoriteRepository {

    override fun getFavoriteProductsStream(): Flow<List<FavoriteProduct>> {
       return favoritesDao.getFavoriteProducts().map { list -> list.map { it.asExternalModel() } }
    }

    override fun checkFavoriteProductStream(productId: String): Flow<Boolean> {
        return favoritesDao.getProductCount(productId).map { it == 1 }

    }

    override suspend fun addFavoriteProduct(product: Product) {
        if (favoritesDao.getProductCount(product.id.toString()).first() == 1) {
            return
        }
        //favoritesDao.insertFavoriteProduct(product.asFavoriteProductEntity())
    }

    override suspend fun removeFavoriteProduct(productId: String) {
        favoritesDao.deleteFavoriteProduct(productId)
    }
}
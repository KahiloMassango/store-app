package com.example.store.core.database.di

import com.example.store.core.database.StoreDatabase
import com.example.store.core.database.dao.AddressesDao
import com.example.store.core.database.dao.CartDao
import com.example.store.core.database.dao.CategoryDao
import com.example.store.core.database.dao.FavoritesDao
import com.example.store.core.database.dao.GenderCategoryDao
import com.example.store.core.database.dao.GenderDao
import com.example.store.core.database.dao.RecentSearchDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object DaosModule {

    @Provides
    fun providesFavoritesDao(
        database: StoreDatabase,
    ): FavoritesDao = database.favoritesDao()

    @Provides
    fun providesCartDao(
        database: StoreDatabase
    ): CartDao  = database.cartDao()

    @Provides
    fun providesAddressesDao(
        database: StoreDatabase
    ): AddressesDao  = database.addressesDao()

    @Provides
    fun providesRecentSearchDao(
        database: StoreDatabase
    ): RecentSearchDao  = database.recentSearchDao()

    @Provides
    fun providesGenderCategoryDao(
        database: StoreDatabase
    ): GenderCategoryDao = database.genderCategoryDao()

    @Provides
    fun providesGenderDao(
        database: StoreDatabase
    ): GenderDao {
        return database.genderDao()
    }

    @Provides
    fun providesCategoryDao(
        database: StoreDatabase
    ): CategoryDao {
        return database.categoryDao()
    }
}
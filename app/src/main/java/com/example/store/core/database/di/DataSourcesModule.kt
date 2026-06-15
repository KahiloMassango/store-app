package com.example.store.core.database.di

import com.example.store.core.database.GenderLocalDataSourceImpl
import com.example.seller_app.core.database.datasources.CategoryLocalDataSource
import com.example.store.core.database.datasources.GenderLocalDataSource
import com.example.store.core.database.CategoryLocalDataSourceImp
import com.example.store.core.database.dao.CategoryDao
import com.example.store.core.database.dao.GenderCategoryDao
import com.example.store.core.database.dao.GenderDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataSourcesModule {

    @Provides
    fun providesCategoryLocalDataSource(
        categoryDao: CategoryDao,
    ): CategoryLocalDataSource = CategoryLocalDataSourceImp(categoryDao)

    @Provides
    fun provideGenderLocalDataSource(
        genderDao: GenderDao,
        genderCategoryDao: GenderCategoryDao
    ): GenderLocalDataSource = GenderLocalDataSourceImpl(genderDao, genderCategoryDao)


}
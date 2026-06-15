package com.example.store.core.database.di

import android.content.Context
import androidx.room.Room
import com.example.store.core.database.StoreDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Provides
    @Singleton
    fun providesStoreDatabase(
        @ApplicationContext context: Context
    ): StoreDatabase = StoreDatabase.getDatabase(context)
}
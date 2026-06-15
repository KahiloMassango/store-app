package com.example.store.core.datastore.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.example.store.core.datastore.AppPreferencesDataSource
import com.example.store.core.datastore.AppPreferencesDataSourceImpl
import com.example.store.core.datastore.JwtTokenManager
import com.example.store.core.datastore.JwtLocalDataSource
import com.example.store.core.datastore.UserPreferences
import com.example.store.core.datastore.UserLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatastoreModule {

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext appContext: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler(
                produceNewData = { emptyPreferences() }
            ),
            produceFile = { appContext.preferencesDataStoreFile("user_preferences") }
        )
    }

    @Provides
    fun providesUserLocalDataSource(
        dataStore: DataStore<Preferences>
    ): UserLocalDataSource = UserPreferences(dataStore)

    @Provides
    fun providesLocalDataSource(
        dataStore: DataStore<Preferences>
    ): JwtLocalDataSource = JwtTokenManager(dataStore)

    @Provides
    fun providesAppPreferencesDataSource(
        dataStore: DataStore<Preferences>
    ): AppPreferencesDataSource = AppPreferencesDataSourceImpl(dataStore)
}
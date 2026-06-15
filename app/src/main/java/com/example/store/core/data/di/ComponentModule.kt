package com.example.store.core.data.di

import android.content.Context
import androidx.work.WorkManager
import com.example.store.core.data.util.ConnectivityManagerNetworkMonitor
import com.example.store.core.data.util.NetworkMonitor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ComponentModule {

    @Provides
    @Singleton
    fun providesWorkManager(
        @ApplicationContext
        context: Context
    ): WorkManager = WorkManager.getInstance(context)

}
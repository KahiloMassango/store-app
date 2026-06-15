package com.example.store.core.network.di

import android.content.Context
import android.location.Geocoder
import com.example.store.core.network.common.AuthenticatedClient
import com.example.store.core.network.common.PublicClient
import com.example.store.core.network.retrofit.DistanceApiService
import com.example.store.core.network.retrofit.GeocodeApiService
import com.example.store.core.network.retrofit.AuthenticatedApiService
import com.example.store.core.network.retrofit.PublicApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

const val BASE_URL = "http://10.0.2.2:8080/v1/"

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideAuthenticationApi(
        @AuthenticatedClient okHttpClient: OkHttpClient
    ): AuthenticatedApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(AuthenticatedApiService::class.java)
    }

    @Provides
    fun providePublicApi(@PublicClient okHttpClient: OkHttpClient): PublicApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(PublicApiService::class.java)
    }


    @Provides
    @Singleton
    fun provideDistanceApiService(): DistanceApiService {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.distancematrix.ai/")
            .build()
            .create(DistanceApiService::class.java)

    }

    @Provides
    @Singleton
    fun provideGeocodeApiService(): GeocodeApiService {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://nominatim.openstreetmap.org/")
            .build()
            .create(GeocodeApiService::class.java)

    }

    @Provides
    @Singleton
    fun provideGeocoderService(
        @ApplicationContext context: Context
    ): Geocoder  = Geocoder(context)
}
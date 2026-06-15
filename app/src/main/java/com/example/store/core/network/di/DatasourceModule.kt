package com.example.store.core.network.di

import com.example.store.core.network.OrderNetworkDatasourceImpl
import com.example.store.core.network.ProductNetworkDatasourceImp
import com.example.store.core.network.StoreNetworkDatasourceImpl
import com.example.store.core.network.SyncNetworkDatasourceImpl
import com.example.store.core.network.TokenNetworkDatasourceImpl
import com.example.store.core.network.UserNetworkDatasourceImpl
import com.example.store.core.network.datasources.OrderNetworkDatasource
import com.example.store.core.network.datasources.ProductNetworkDatasource
import com.example.store.core.network.datasources.StoreNetworkDatasource
import com.example.store.core.network.datasources.SyncNetworkDatasource
import com.example.store.core.network.datasources.TokenNetworkDatasource
import com.example.store.core.network.datasources.UserNetworkDatasource
import com.example.store.core.network.retrofit.AuthenticatedApiService
import com.example.store.core.network.retrofit.PublicApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatasourceModule {

    @Provides
    fun providesTokenNetworkDatasource(
        apiService: PublicApiService
    ): TokenNetworkDatasource = TokenNetworkDatasourceImpl(apiService)

    @Provides
    fun providesProductNetworkDatasource(
        apiService: PublicApiService
    ): ProductNetworkDatasource = ProductNetworkDatasourceImp(apiService)

    @Provides
    fun providesStoreNetworkDatasource(
        apiService: AuthenticatedApiService
    ): StoreNetworkDatasource = StoreNetworkDatasourceImpl(apiService)

    @Provides
    fun providesUserNetworkDatasource(
        publicApiService: PublicApiService,
        authenticatedApiService: AuthenticatedApiService
    ): UserNetworkDatasource = UserNetworkDatasourceImpl(
        publicApiService,
        authenticatedApiService
    )

    @Provides
    fun providesOrderNetworkDatasource(
        apiService: AuthenticatedApiService
    ): OrderNetworkDatasource = OrderNetworkDatasourceImpl(apiService)

    @Provides
    fun providesSyncNetworkDatasource(
        apiService: PublicApiService
    ): SyncNetworkDatasource = SyncNetworkDatasourceImpl(apiService)
}
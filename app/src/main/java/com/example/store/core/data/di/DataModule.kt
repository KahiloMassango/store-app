package com.example.store.core.data.di

import android.content.Context
import android.location.Geocoder
import androidx.work.WorkManager
import com.example.store.core.data.GenderRepositoryImpl
import com.example.store.core.data.repository.GenderRepository
import com.example.seller_app.core.database.datasources.CategoryLocalDataSource
import com.example.store.core.database.datasources.GenderLocalDataSource
import com.example.store.core.data.AccountRepositoryImpl
import com.example.store.core.data.CategoryRepositoryImpl
import com.example.store.core.data.AddressRepositoryImpl
import com.example.store.core.data.CartRepositoryImpl
import com.example.store.core.data.DefaultFavoriteRepository
import com.example.store.core.data.DefaultLocationRepository
import com.example.store.core.data.ProductRepositoryImpl
import com.example.store.core.data.RecentSearchRepositoryImpl
import com.example.store.core.data.OrderRepositoryImpl
import com.example.store.core.data.StoreRepositoryImpl
import com.example.store.core.data.SyncRepositoryImpl
import com.example.store.core.data.repository.AccountRepository
import com.example.store.core.data.repository.AddressRepository
import com.example.store.core.data.repository.CartRepository
import com.example.store.core.data.repository.CategoryRepository
import com.example.store.core.data.repository.FavoriteRepository
import com.example.store.core.data.repository.LocationRepository
import com.example.store.core.data.repository.OrderRepository
import com.example.store.core.data.repository.ProductRepository
import com.example.store.core.data.repository.RecentSearchRepository
import com.example.store.core.data.repository.StoreRepository
import com.example.store.core.data.repository.SyncRepository
import com.example.store.core.data.util.ConnectivityManagerNetworkMonitor
import com.example.store.core.data.util.NetworkMonitor
import com.example.store.core.data.workmanager.SyncManager
import com.example.store.core.database.dao.AddressesDao
import com.example.store.core.database.dao.CartDao
import com.example.store.core.database.dao.FavoritesDao
import com.example.store.core.database.dao.RecentSearchDao
import com.example.store.core.datastore.AppPreferencesDataSource
import com.example.store.core.datastore.JwtLocalDataSource
import com.example.store.core.datastore.UserLocalDataSource
import com.example.store.core.network.datasources.OrderNetworkDatasource
import com.example.store.core.network.datasources.ProductNetworkDatasource
import com.example.store.core.network.datasources.StoreNetworkDatasource
import com.example.store.core.network.datasources.SyncNetworkDatasource
import com.example.store.core.network.datasources.UserNetworkDatasource
import com.example.store.core.network.retrofit.DistanceApiService
import com.example.store.core.network.retrofit.GeocodeApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun providesSyncRepository(
        syncNetworkDatasource: SyncNetworkDatasource,
        appPreferencesDataSource: AppPreferencesDataSource,
        genderRepository: GenderRepository,
        categoryRepository: CategoryRepository
    ): SyncRepository = SyncRepositoryImpl(
        syncNetworkDatasource,
        appPreferencesDataSource,
        categoryRepository,
        genderRepository
    )

    @Provides
    fun providesSyncManager(
        workManager: WorkManager,
    ): SyncManager = SyncManager(workManager)


    @Provides
    fun provideGenderRepository(
        localDataSource: GenderLocalDataSource,
    ): GenderRepository = GenderRepositoryImpl(localDataSource)

    @Provides
    fun provideCategoryRepository(
        localDataSource: CategoryLocalDataSource,
    ): CategoryRepository = CategoryRepositoryImpl(localDataSource)


    @Provides
    fun providesFavoriteRepository(
        favoritesDao: FavoritesDao
    ): FavoriteRepository = DefaultFavoriteRepository(favoritesDao)

    @Provides
    fun providesStoreRepository(
        datasource: StoreNetworkDatasource
    ): StoreRepository = StoreRepositoryImpl(datasource)

    @Provides
    fun providesAddressesRepository(
        addressesDao: AddressesDao
    ): AddressRepository = AddressRepositoryImpl(addressesDao)

    @Provides
    fun providesCartRepository(
        cartDao: CartDao
    ): CartRepository = CartRepositoryImpl(cartDao)


    @Provides
    fun providesRecentSearchRepository(
        recentSearchDao: RecentSearchDao
    ): RecentSearchRepository = RecentSearchRepositoryImpl(recentSearchDao)

    @Provides
    fun providesUserRepository(
        userNetworkDatasource: UserNetworkDatasource,
        userLocalDataSource: UserLocalDataSource,
        jwtDataSource: JwtLocalDataSource
    ): AccountRepository = AccountRepositoryImpl(
        userNetworkDatasource,
        userLocalDataSource,
        jwtDataSource
    )

    @Provides
    fun providesProductRepository(
        networkDatasource: ProductNetworkDatasource
    ): ProductRepository = ProductRepositoryImpl(networkDatasource)


 @Provides
    fun providesOrderRepository(
        networkDatasource: OrderNetworkDatasource
    ): OrderRepository = OrderRepositoryImpl(networkDatasource)

    @Provides
    fun providesLocationRepository(
        geocodeApiService: GeocodeApiService,
        geocodeService: Geocoder,
        distanceApiService: DistanceApiService
    ): LocationRepository =
        DefaultLocationRepository(
            geocodeApiService,
            geocodeService,
            distanceApiService
        )

    @Provides
    fun providesNetworkMonitor(
        @ApplicationContext context: Context
    ): NetworkMonitor = ConnectivityManagerNetworkMonitor(
        context
    )


}
package com.example.store.core.data.di

import android.content.Context
import androidx.work.WorkManager
import com.example.store.core.data.DefaultFavoriteRepository
import com.example.store.core.data.SyncRepositoryImpl
import com.example.store.core.data.repository.*
import com.example.store.core.data.util.ConnectivityManagerNetworkMonitor
import com.example.store.core.data.util.NetworkMonitor
import com.example.store.core.data.workmanager.SyncManager
import com.example.store.core.database.dao.FavoritesDao
import com.example.store.core.datastore.AppPreferencesDataSource
import com.example.store.core.network.datasources.SyncNetworkDatasource
import com.example.store.core.testing.fake_repositories.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object FakeDataModule {

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
    fun provideGenderRepository(): GenderRepository = FakeGenderRepository()

    @Provides
    fun provideCategoryRepository(): CategoryRepository = FakeCategoryRepository()

    @Provides
    fun providesFavoriteRepository(
        favoritesDao: FavoritesDao
    ): FavoriteRepository = DefaultFavoriteRepository(favoritesDao)

    @Provides
    fun providesStoreRepository(): StoreRepository = FakeStoreRepository()

    @Provides
    fun providesAddressesRepository(): AddressRepository = FakeAddressRepository()

    @Provides
    fun providesCartRepository(): CartRepository = FakeCartRepository()

    @Provides
    fun providesRecentSearchRepository(): RecentSearchRepository = FakeRecentSearchRepository()

    @Provides
    fun providesUserRepository(): AccountRepository = FakeAccountRepository()

    @Provides
    fun providesProductRepository(): ProductRepository = FakeProductRepository()

    @Provides
    fun providesOrderRepository(): OrderRepository = FakeOrderRepository()

    @Provides
    fun providesLocationRepository(): LocationRepository = FakeLocationRepository()

    @Provides
    fun providesNetworkMonitor(
        @ApplicationContext context: Context
    ): NetworkMonitor = ConnectivityManagerNetworkMonitor(context)
}

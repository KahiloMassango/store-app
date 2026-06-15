package com.example.store.core.data

import android.util.Log
import com.example.store.core.data.repository.GenderRepository
import com.example.store.core.data.repository.CategoryRepository
import com.example.store.core.data.repository.SyncRepository
import com.example.store.core.datastore.AppPreferencesDataSource
import com.example.store.core.network.datasources.SyncNetworkDatasource
import java.io.IOException

class SyncRepositoryImpl(
    private val syncNetworkDatasource: SyncNetworkDatasource,
    private val appPreferencesDataSource: AppPreferencesDataSource,
    private val categoryRepository: CategoryRepository,
    private val genderRepository: GenderRepository
): SyncRepository {

    override suspend fun sync(): Result<Unit> {
        val remoteTimestamp = syncNetworkDatasource.getLastUpdate()
            .onFailure { Log.e("SyncRepositoryImpl", "sync: remote timestampe ex: ", it) }
            .getOrNull()
            ?: return Result.failure(IOException())
        val localTimestamp = appPreferencesDataSource.getLastUpdated()

        if (localTimestamp == null || localTimestamp < remoteTimestamp) {
            Log.d("SyncRepositoryImpl", "sync: need update")
            try {
                Log.d("SyncRepositoryImpl", "sync: trying update")
                val syncData = syncNetworkDatasource.sync().getOrNull()
                    ?: return Result.failure(IOException())
                categoryRepository.sync(syncData.categories)
                genderRepository.sync(syncData.genders)
                genderRepository.syncGenderCategories(syncData.genderCategoryRelations)
                appPreferencesDataSource.setLastUpdated(remoteTimestamp)
                Log.d("SyncRepositoryImpl", "sync: success")
                return Result.success(Unit)
            } catch (e: Exception) {
                Log.d("SyncRepositoryImpl", "sync: exception on update $e")
                return Result.failure(IOException())
            }
        } else {
            Log.d("SyncRepositoryImpl", "sync: success not need to update")
            return Result.success(Unit)

        }

    }

}
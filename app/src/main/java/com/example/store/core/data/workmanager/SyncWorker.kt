package com.example.store.core.data.workmanager

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.store.core.data.repository.SyncRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class SyncWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    @Assisted private val syncRepository: SyncRepository,
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result {
        val syncResponse = syncRepository.sync()
        return if (syncResponse.isSuccess) {
            Result.success()
        } else {
            Result.Retry()
        }
    }
}
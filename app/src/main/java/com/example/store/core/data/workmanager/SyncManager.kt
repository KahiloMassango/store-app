package com.example.store.core.data.workmanager

import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager

class SyncManager(
    private val workManager: WorkManager,
) {

    fun sync() {
        val workRequest = OneTimeWorkRequest.Builder(SyncWorker::class.java)
            .build()

        workManager.enqueue(workRequest)
    }

}
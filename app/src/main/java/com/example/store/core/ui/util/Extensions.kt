package com.example.store.core.ui.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import java.text.NumberFormat
import java.util.Locale

fun <T> Flow<T>.hotFlow(scope: CoroutineScope, initialValue: T): StateFlow<T> {
    return this.stateIn(
        scope,
        SharingStarted.WhileSubscribed(5_000),
        initialValue
    )
}


fun Int.toCurrency(): String {

    val formattedText = NumberFormat.getNumberInstance(Locale("pt", "AO")).format(this)

    // Append the currency symbol
    return "$formattedText Kz"
}

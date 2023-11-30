package com.zig.presentation.core.extensions

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart

internal suspend inline fun <T> Flow<T>.observe(
    crossinline onProgress: () -> Unit = {},
    crossinline onSuccess: suspend (value: T) -> Unit,
    crossinline onError: (Throwable) -> Unit = {}
) = apply {
    onStart {
        onProgress()
    }.catch {
        onError(it)
    }.collect {
        onSuccess(it)
    }
}.flowOn(Dispatchers.Main)

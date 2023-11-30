package com.zig.presentation.core.state

sealed class SimpleState<out T> {
    object Loading : SimpleState<Nothing>()
    object Empty : SimpleState<Nothing>()
    data class Loaded<T>(val data: T) : SimpleState<T>()
    data class Failed<T>(val throwable: Throwable) : SimpleState<T>()
}

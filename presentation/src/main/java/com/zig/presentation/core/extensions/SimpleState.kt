package com.zig.presentation.core.extensions

import androidx.lifecycle.LiveData
import com.zig.presentation.core.state.SimpleState
import com.zig.presentation.core.state.SingleLiveEvent

typealias MutableState<T> = SingleLiveEvent<SimpleState<T>>
typealias LiveState<T> = LiveData<SimpleState<T>>

fun <T> MutableState<T>.onLoading(): () -> Unit = {
    value = SimpleState.Loading
}

fun <T> MutableState<T>.onSuccess(): suspend (T) -> Unit = {
    value = if (it is List<*> && it.isEmpty()) SimpleState.Empty
    else SimpleState.Loaded(it)
}

fun <T> MutableState<T>.onError(): (Throwable) -> Unit = {
    value = SimpleState.Failed(it)
}

internal fun <T> MutableState<T>.onMaybe(): suspend (T?) -> Unit = {
    if (it == null) postValue(SimpleState.Empty)
    else postValue(SimpleState.Loaded(it))
}

fun MutableState<Unit>.onComplete(): () -> Unit = {
    value = SimpleState.Loaded(Unit)
}


fun <T> MutableState<T>.hasLoaded(): Boolean {
    return if (value is SimpleState.Loaded) {
        value = SimpleState.Loaded((value as SimpleState.Loaded).data)
        true
    } else false
}

fun <T> LiveState<T>.getLoadedData(): T? {
    return if (value is SimpleState.Loaded) {
        (value as SimpleState.Loaded).data
    } else null
}

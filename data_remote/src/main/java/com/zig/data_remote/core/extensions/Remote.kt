package com.zig.data_remote.core.extensions

import com.zig.data.exception.NetworkException
import com.zig.data.exception.TimeoutException
import com.zig.data.exception.UnexpectedException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import timber.log.Timber
import java.net.SocketTimeoutException
import java.net.UnknownHostException


fun <T> Any.exec(block: suspend FlowCollector<T>.() -> T): Flow<T> = flow {
    try {
        emit(block())
    } catch (e: HttpException) {
        Timber.e(e)
        throw UnexpectedException()
    } catch (e: SocketTimeoutException) {
        Timber.e(e)
        throw TimeoutException()
    } catch (e: UnknownHostException) {
        Timber.e(e)
        throw NetworkException()
    } catch (e: Exception) {
        Timber.e(e)
        throw UnexpectedException()
    }
}.flowOn(Dispatchers.IO)

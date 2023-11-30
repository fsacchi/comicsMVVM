package com.zig.data_remote.core.model

data class RemoteResponse<T>(
    val data: T,
    val status: String
)

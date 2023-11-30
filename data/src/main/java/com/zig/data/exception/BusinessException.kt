package com.zig.data.exception

open class BusinessException : Throwable {
    override val message: String get() = super.message ?: ""

    constructor(message: String) : super(message)
    constructor(message: String, cause: Throwable) : super(message, cause)
}

package net.huray.backend.http.exception

import net.huray.backend.http.exception.code.ErrorCode

class BaseException(
    error: ErrorCode,
    value: String? = null
) : RuntimeException() {
    val code: Int
    val reason: String

    init {
        code = error.code
        reason = if(value != null) ("${error.reason} ${error.detail.replace("{VALUE}", value)}") else error.reason
    }
}
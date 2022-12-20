package net.huray.backend.http.exception

import net.huray.backend.http.exception.code.ErrorCode

class BaseException(
    val code: Int,
    val reason: String
) : RuntimeException() {
    constructor(error: ErrorCode, value: Any? = null) : this(
        error.code,
        if (value != null) ("${error.reason} ${error.detail.replace("{VALUE}", value.toString())}") else error.reason
    )
}
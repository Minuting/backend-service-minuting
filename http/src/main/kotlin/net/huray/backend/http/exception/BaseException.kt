package net.huray.backend.http.exception

open class BaseException(
    val code: Int,
    val reason: String
) : RuntimeException()
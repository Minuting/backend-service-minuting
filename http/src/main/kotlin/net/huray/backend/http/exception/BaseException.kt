package net.huray.backend.http.exception

import net.huray.backend.http.res.BaseResult

open class BaseException(
    /**
     * http response status code. 4xx, 5xx
     */
    val status: Int,
    /**
     * i18n message code.
     */
    var code: String,
    /**
     * i18n default message format
     */
    var default: String? = null,
    /**
     * i18n message format arguments
     */
    var args: Array<out Any?>? = null
) : RuntimeException(code) {

    fun toResult() = BaseResult(this.javaClass.simpleName).addError(code, default, args)
}
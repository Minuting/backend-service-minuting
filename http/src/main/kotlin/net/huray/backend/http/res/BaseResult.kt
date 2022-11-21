package net.huray.backend.http.res

import java.io.Serializable

open class BaseResult() : Serializable {
    var error: String? = null
    var errors: MutableList<BaseError>? = null

    constructor(errorName: String) : this() {
        this.error = errorName
    }

    fun addError(code: String, default: String?, args: Array<out Any?>? = null): BaseResult {
        if (error == null) error = "UnknownError"

        if (errors != null) errors!!.add(BaseError(code, default, args))
        else errors = mutableListOf(BaseError(code, default, args))

        return this
    }
}
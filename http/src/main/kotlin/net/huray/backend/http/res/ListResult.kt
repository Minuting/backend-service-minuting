package net.huray.backend.http.res

open class ListResult<T>() : BaseResult() {
    val list = mutableListOf<T>()

    constructor(initList: Iterable<T>? = null) : this() {
        if (initList != null) list.addAll(initList)
    }

    fun addAll(others: Iterable<T>) = list.addAll(others)

    fun add(element: T) = list.add(element)

    override fun toString() = "ListResult:$list"
}
package net.huray.backend.http.res

import java.io.Serializable

class DoneResult(var result: Boolean = true, var id: Number? = null) : BaseResult(), Serializable
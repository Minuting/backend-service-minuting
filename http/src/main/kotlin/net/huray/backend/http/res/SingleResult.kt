package net.huray.backend.http.res

import java.io.Serializable

class SingleResult<T>(val value: T) : BaseResult(), Serializable

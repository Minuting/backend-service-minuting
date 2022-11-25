package net.huray.backend.minuting.entity

import java.io.Serializable

data class SpaceTagId(
    private var space: Long? = null,
    private var tag: Long? = null
) : Serializable
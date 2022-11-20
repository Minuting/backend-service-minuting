package net.huray.backend.minuting.entity

import java.io.Serializable

data class SpaceTagId(
    private val space: SpaceEntity? = null,
    private val tag: TagEntity? = null
) : Serializable
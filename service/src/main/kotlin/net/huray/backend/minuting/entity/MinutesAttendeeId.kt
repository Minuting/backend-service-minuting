package net.huray.backend.minuting.entity

import java.io.Serializable

data class MinutesAttendeeId(
    private val minutes: String = "",
    private val member: String = ""
) : Serializable
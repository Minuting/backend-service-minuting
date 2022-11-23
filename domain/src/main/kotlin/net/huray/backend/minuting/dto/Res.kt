package net.huray.backend.minuting.dto

import net.huray.backend.minuting.enums.MemberType

object Res {
    class CompanyRes(
        val id: Long,
        val name: String,
        val ceo: String,
        val telNumber: String,
        val address: String
    )

    class TeamRes(
        val id: Long,
        val name: String
    )

}
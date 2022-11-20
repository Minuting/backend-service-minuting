package net.huray.backend.minuting.dto

import net.huray.backend.minuting.enums.MemberType

object Res {

    open class SpaceRes(
        val id: Long,
        val name: String,
        val icon: String,
        val isPublic: Boolean
    )

    class PublicSpaceRes(
        id: Long,
        name: String,
        icon: String,
        isPublic: Boolean
    ) : SpaceRes(id, name, icon, isPublic) {

        var isJoined: Boolean = false

    }

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

    class BaseInfoRes(
        val name: String,
        val memberType: MemberType,
        val company: CompanyRes,
        val team: TeamRes,
        val spaceList: List<SpaceRes>
    )

}
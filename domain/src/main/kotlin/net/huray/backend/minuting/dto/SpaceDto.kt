package net.huray.backend.minuting.dto

import io.swagger.annotations.ApiModelProperty
import net.huray.backend.minuting.enums.SpacePermissionType

object SpaceDto {

    open class SpaceBase(
        @ApiModelProperty("스페이스 ID", required = true)
        val id: Long,
        @ApiModelProperty("스페이스 명", required = true)
        val name: String,
        @ApiModelProperty("스페이스 Icon", required = true)
        val icon: String,
        @ApiModelProperty("스페이스 공개여부", required = true)
        val isPublic: Boolean
    )

    open class SpaceSimple(
        id: Long,
        name: String,
        icon: String,
        isPublic: Boolean
    ) : SpaceBase(id, name, icon, isPublic)

    open class SpacePublic(
            id: Long,
            name: String,
            icon: String,
            isPublic: Boolean
    ) : SpaceBase(id, name, icon, isPublic) {
        @ApiModelProperty("스페이스 참가여부", required = true)
        var isJoined: Boolean = false

    }

    open class SpaceDetail(
            id: Long,
            name: String,
            icon: String,
            isPublic: Boolean,
    ) : SpaceBase(id, name, icon, isPublic) {
        @ApiModelProperty("스페이스 참여 권한", required = true)
        var spacePermissionType: SpacePermissionType = SpacePermissionType.GUEST
    }

}
package net.huray.backend.minuting.dto

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import net.huray.backend.minuting.enums.PermissionType
import net.huray.backend.minuting.enums.SpacePermissionType
import java.util.*

object PermissionDto {

    open class PermissionBase(
        @ApiModelProperty("권한 ID", required = true)
        var id: Long,
        @ApiModelProperty("권한 타입", required = true)
        var type: PermissionType,
        @ApiModelProperty("권한 스페이스", required = true)
        var space: SpaceDto.SpaceBase,
        @ApiModelProperty("권한 유저", required = true)
        var member: MemberDto.MemberBase
    )

    class PermissionSimple(
        id: Long,
        type: PermissionType,
        space: SpaceDto.SpaceBase,
        member: MemberDto.MemberBase
    ) : PermissionBase(id, type, space, member)


    @ApiModel("권한 등록 요청 정보")
    class CreateReq(
        @ApiModelProperty("권한 타입", required = true)
        val type: PermissionType,
        @ApiModelProperty("권한 멤버 아이디", required = true)
        val memberId: UUID
    )

    @ApiModel("권한 수정 요청 정보")
    class UpdateReq(
        @ApiModelProperty("권한 타입", required = true)
        val type: PermissionType,
        @ApiModelProperty("권한 멤버 아이디", required = true)
        val memberId: UUID
    )
}
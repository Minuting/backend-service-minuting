package net.huray.backend.minuting.dto

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import net.huray.backend.minuting.enums.MemberType
import net.huray.backend.minuting.enums.PermissionType
import net.huray.backend.minuting.enums.SpacePermissionType
import java.util.*

object MemberDto {
    open class MemberBase(
        @ApiModelProperty("사용자 이름")
        val name: String,
        @ApiModelProperty("사용자 타입")
        val memberType: MemberType
    )

    class MemberSimple(
        name: String,
        memberType: MemberType,
    ) : MemberBase(name, memberType)

    class MemberDetail(
        @ApiModelProperty("사용자 ID", required = true)
        var id: UUID,
        name: String,
        memberType: MemberType,
    ) : MemberBase(name, memberType)

}
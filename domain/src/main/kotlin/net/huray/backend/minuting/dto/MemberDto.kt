package net.huray.backend.minuting.dto

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import net.huray.backend.minuting.enums.MemberType
import springfox.documentation.annotations.ApiIgnore
import java.util.*

object MemberDto {

    @ApiIgnore
    open class MemberBase(
        @ApiModelProperty("사용자 이름")
        val name: String,
        @ApiModelProperty("사용자 타입")
        val memberType: MemberType
    )

    @ApiModel("유저 기본 응답 정보")
    class MemberSimple(
        name: String,
        memberType: MemberType,
    ) : MemberBase(name, memberType)

    @ApiModel("유저 상세 응답 정보")
    class MemberDetail(
        @ApiModelProperty("사용자 ID", required = true)
        var id: UUID,
        name: String,
        memberType: MemberType,
    ) : MemberBase(name, memberType)

}
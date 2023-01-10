package net.huray.backend.minuting.dto

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import net.huray.backend.minuting.enums.MemberType
import springfox.documentation.annotations.ApiIgnore
import java.util.*

object MemberDto {

    @ApiIgnore
    open class BaseRes(
        @ApiModelProperty("사용자 ID", required = true)
        var id: UUID,
        @ApiModelProperty("사용자 이름")
        val name: String,
        @ApiModelProperty("사용자 타입")
        val memberType: MemberType
    )

    @ApiModel("유저 기본 응답 정보")
    class SimpleRes(
        id: UUID,
        name: String,
        memberType: MemberType,
    ) : BaseRes(id, name, memberType)

    @ApiModel("유저 상세 응답 정보")
    class DetailRes(
        id: UUID,
        name: String,
        memberType: MemberType,
    ) : BaseRes(id, name, memberType)

}
package net.huray.backend.minuting.dto

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import net.huray.backend.minuting.enums.MemberType
import springfox.documentation.annotations.ApiIgnore

object BaseInfoDto {

    @ApiIgnore
    open class BaseInfo(
            @ApiModelProperty("사용자 이름")
            val name: String,
            @ApiModelProperty("사용자 타입")
            val memberType: MemberType,
            @ApiModelProperty("회사 정보")
            val company: Res.CompanyRes,
            @ApiModelProperty("팀 정보")
            val team: Res.TeamRes,
            @ApiModelProperty("참가 스페이스 목록")
            val spaceList: List<SpaceDto.SpaceSimple>
    )

    @ApiModel("댓글 기본 응답 정보")
    open class BaseInfoSimple(
            name: String,
            memberType: MemberType,
            company: Res.CompanyRes,
            team: Res.TeamRes,
            spaceList: List<SpaceDto.SpaceSimple>
    ) : BaseInfo(name, memberType, company, team, spaceList)


}
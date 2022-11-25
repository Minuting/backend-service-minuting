package net.huray.backend.minuting.dto

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import net.huray.backend.minuting.enums.MemberType
import springfox.documentation.annotations.ApiIgnore
import java.util.*

object UserInfoDto {

    @ApiIgnore
    open class UserInfoBase(
        val userId: UUID,
        val name: String
    )

    @ApiIgnore
    open class UserInfo(
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

    @ApiModel("유저 기본 응답 정보")
    open class UserInfoSimple(
            name: String,
            memberType: MemberType,
            company: Res.CompanyRes,
            team: Res.TeamRes,
            spaceList: List<SpaceDto.SpaceSimple>
    ) : UserInfo(name, memberType, company, team, spaceList)


}
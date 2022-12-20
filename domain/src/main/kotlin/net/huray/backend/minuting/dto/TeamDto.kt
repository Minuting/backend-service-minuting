package net.huray.backend.minuting.dto

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import springfox.documentation.annotations.ApiIgnore

object TeamDto {

    @ApiIgnore
    open class TeamBase(
        @ApiModelProperty("팀 ID", required = true)
        val id: Long,
        @ApiModelProperty("팀 타입", required = true)
        val name: String,
        @ApiModelProperty("부모 팀 ID", required = true)
        var pId: Long? = null
    )

    @ApiModel("팀 기본 응답 정보")
    class TeamSimple(
        id: Long,
        name: String,
        pId: Long? = null,
    ) : TeamBase(id, name, pId)

}
package net.huray.backend.minuting.dto

import io.swagger.annotations.ApiModelProperty

object TeamDto {
    open class TeamBase(
        @ApiModelProperty("팀 ID", required = true)
        val id: Long,
        @ApiModelProperty("팀 타입", required = true)
        val name: String,
        @ApiModelProperty("부모 팀 ID", required = true)
        var pId: Long? = null
    )

    class TeamSimpleDto(
        id: Long,
        name: String,
        pId: Long? = null,
    ) : TeamBase(id, name, pId)

}
package net.huray.backend.minuting.dto

import io.swagger.annotations.ApiModelProperty
import net.huray.backend.minuting.enums.TagType

object TagDto {
    open class TagBase(
        @ApiModelProperty("태그 ID", required = true)
        var id: Long = 0L,
        @ApiModelProperty("태그명", required = true)
        var name: String = "",
        @ApiModelProperty("태그 타입", required = true)
        var type: TagType = TagType.MINUTES,
        @ApiModelProperty("태그 컬러코드", required = true)
        var color: String = "",
        @ApiModelProperty("태그 정렬순서", required = true)
        var orderNum: Int = 0,
    )

    class TagSimple(
        id: Long,
        name: String,
        type: TagType,
        color: String,
        orderNum: Int,
    ) : TagBase(id, name, type, color, orderNum)

}
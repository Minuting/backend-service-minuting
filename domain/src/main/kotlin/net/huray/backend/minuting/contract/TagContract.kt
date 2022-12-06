package net.huray.backend.minuting.contract

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import net.huray.backend.http.res.ListResult
import net.huray.backend.minuting.contract.Endpoint.TAGS
import net.huray.backend.minuting.dto.TagDto
import net.huray.backend.minuting.enums.TagType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Api(tags = ["태그 API"])
interface TagContract {

    @ApiOperation("타입별 태그 목록 (타입 : SPACE/MINUTES)")
    @GetMapping("${TAGS}/{type}")
    fun listByType(@PathVariable(name = "type") type: TagType): ListResult<TagDto.TagSimple>

}
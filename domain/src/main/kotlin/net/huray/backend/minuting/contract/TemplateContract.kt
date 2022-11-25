package net.huray.backend.minuting.contract

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import net.huray.backend.http.res.DoneResult
import net.huray.backend.http.res.ListResult
import net.huray.backend.http.res.SingleResult
import net.huray.backend.minuting.contract.Endpoint.TEMPLATE
import net.huray.backend.minuting.dto.TemplateDto
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@Api(tags = ["템플릿 API"])
interface TemplateContract {

    @ApiOperation("템플릿 등록")
    @PostMapping(TEMPLATE, produces = [MediaType.APPLICATION_JSON_VALUE])
    fun create(req: TemplateDto.CreateReq): SingleResult<TemplateDto.TemplateSimple>

    @ApiOperation("템플릿 리스트")
    @GetMapping(TEMPLATE, produces = [MediaType.APPLICATION_JSON_VALUE])
    fun listSimple(): ListResult<TemplateDto.TemplateSimple>

    @ApiOperation("템플릿 상세 정보")
    @GetMapping("${TEMPLATE}/{templateId}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getDetail(@PathVariable templateId: Long): SingleResult<TemplateDto.TemplateDetail>

    @ApiOperation("템플릿 수정")
    @PutMapping("${TEMPLATE}/{templateId}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun update(
        @PathVariable templateId: Long,
        req: TemplateDto.UpdateReq
    ): DoneResult

    @ApiOperation("템플릿 삭제")
    @DeleteMapping("${TEMPLATE}/{templateId}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun delete(@PathVariable templateId: Long): DoneResult

}
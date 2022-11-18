package net.huray.backend.minuting.contract

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import net.huray.backend.minuting.contract.Endpoint.MINUTES
import net.huray.backend.minuting.dto.MinutesDto
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@Api(tags = ["회의록 API"])
interface MinutesContract {

    @ApiOperation("회의록 생성")
    @PostMapping(MINUTES, produces = [MediaType.APPLICATION_JSON_VALUE])
    fun create(req: MinutesDto.CreateReq): MinutesDto.MinutesSimple

    @ApiOperation("회의록 리스트")
    @GetMapping(MINUTES, produces = [MediaType.APPLICATION_JSON_VALUE])
    fun listSimple(): List<MinutesDto.MinutesSimple>

    @ApiOperation("회의록 상세 조회")
    @GetMapping("${MINUTES}/{id}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getDetail(@PathVariable id: Long): MinutesDto.MinutesDetail

    @ApiOperation("회의록 갱신(업데이트)")
    @PutMapping("${MINUTES}/{id}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun update(
        @PathVariable id: Long,
        req: MinutesDto.UpdateReq
    )

    @ApiOperation("회의록 삭제")
    @DeleteMapping("${MINUTES}/{id}")
    fun delete(@PathVariable id: Long)

}
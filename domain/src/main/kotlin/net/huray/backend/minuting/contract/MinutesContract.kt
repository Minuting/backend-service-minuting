package net.huray.backend.minuting.contract

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import net.huray.backend.http.res.DoneResult
import net.huray.backend.http.res.ListResult
import net.huray.backend.http.res.SingleResult
import net.huray.backend.minuting.contract.Endpoint.MINUTES
import net.huray.backend.minuting.dto.MinutesDto
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@Api(tags = ["회의록 API"])
interface MinutesContract {

    @ApiOperation("회의록 생성")
    @PostMapping(MINUTES, produces = [MediaType.APPLICATION_JSON_VALUE])
    fun create(
        @RequestBody req: MinutesDto.CreateReq
    ): SingleResult<MinutesDto.MinutesSimple>

    @ApiOperation("회의록 목록")
    @GetMapping(MINUTES, produces = [MediaType.APPLICATION_JSON_VALUE])
    fun listSimple(
        req: MinutesDto.ListReq
    ): ListResult<MinutesDto.MinutesSimple>

    @ApiOperation("회의록 상세 조회")
    @GetMapping("${MINUTES}/{minutesId}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getDetail(@PathVariable minutesId: Long): SingleResult<MinutesDto.MinutesDetail>

    @ApiOperation("회의록 수정")
    @PutMapping("${MINUTES}/{minutesId}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun update(
        @PathVariable minutesId: Long,
        @RequestBody req: MinutesDto.UpdateReq
    ): DoneResult

    @ApiOperation("회의록 삭제")
    @DeleteMapping("${MINUTES}/{minutesId}")
    fun delete(@PathVariable minutesId: Long): DoneResult

}

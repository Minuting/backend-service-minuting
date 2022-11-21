package net.huray.backend.minuting.contract

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import net.huray.backend.http.res.ListResult
import net.huray.backend.http.res.SingleResult
import net.huray.backend.minuting.contract.Endpoint.MINUTES
import net.huray.backend.minuting.contract.Endpoint.SPACES
import net.huray.backend.minuting.dto.MinutesDto
import net.huray.backend.minuting.dto.Res
import net.huray.backend.minuting.dto.SpaceDto
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@Api(tags = ["스페이스 API"])
interface SpaceContract {

    @ApiOperation("스페이스 생성")
    @PostMapping(SPACES, produces = [MediaType.APPLICATION_JSON_VALUE])
    fun create(req: MinutesDto.CreateReq): SingleResult<SpaceDto.SpaceSimple>

    @ApiOperation("스페이스 상세 조회")
    @GetMapping("${SPACES}/{id}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun get(@PathVariable id: Long): SingleResult<SpaceDto.SpaceDetail>

    @ApiOperation("공개 스페이스 리스트")
    @GetMapping("${SPACES}/public", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun listPublic(): ListResult<SpaceDto.SpacePublic>

    @ApiOperation("스페이스 갱신(업데이트)")
    @PutMapping("${SPACES}/{id}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun update(
        @PathVariable id: Long,
        req: MinutesDto.UpdateReq
    )

    @ApiOperation("스페이스 삭제")
    @DeleteMapping("${SPACES}/{id}")
    fun delete(@PathVariable id: Long)

}
package net.huray.backend.minuting.contract

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import net.huray.backend.http.res.DoneResult
import net.huray.backend.http.res.ListResult
import net.huray.backend.http.res.SingleResult
import net.huray.backend.minuting.contract.Endpoint.MINUTES_COMMENTS
import net.huray.backend.minuting.dto.CommentDto
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@Api(tags = ["댓글 API"])
interface CommentContract {

    @ApiOperation("댓글 등록")
    @PostMapping(MINUTES_COMMENTS, produces = [MediaType.APPLICATION_JSON_VALUE])
    fun add(
        @PathVariable minutesId: Long,
        @RequestBody req: CommentDto.CreateReq
    ): SingleResult<CommentDto.CommentSimple>

    @ApiOperation("댓글 목록")
    @GetMapping(MINUTES_COMMENTS, produces = [MediaType.APPLICATION_JSON_VALUE])
    fun listSimple(
        @PathVariable minutesId: Long
    ): ListResult<CommentDto.CommentSimple>

    @ApiOperation("댓글 상세 정보")
    @GetMapping("${MINUTES_COMMENTS}/{commentId}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getDetail(
        @PathVariable minutesId: Long,
        @PathVariable commentId: Long
    ): SingleResult<CommentDto.CommentDetail>

    @ApiOperation("댓글 수정")
    @PutMapping("${MINUTES_COMMENTS}/{commentId}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun update(
        @PathVariable minutesId: Long,
        @PathVariable commentId: Long,
        @RequestBody req: CommentDto.UpdateReq
    ): DoneResult

    @ApiOperation("댓글 삭제")
    @DeleteMapping("${MINUTES_COMMENTS}/{commentId}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun delete(
        @PathVariable minutesId: Long,
        @PathVariable commentId: Long
    ): DoneResult

}
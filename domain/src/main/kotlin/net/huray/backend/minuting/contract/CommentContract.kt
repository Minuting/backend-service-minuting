package net.huray.backend.minuting.contract

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import net.huray.backend.http.res.DoneResult
import net.huray.backend.http.res.ListResult
import net.huray.backend.http.res.SingleResult
import net.huray.backend.minuting.contract.Endpoint.COMMENTS
import net.huray.backend.minuting.dto.CommentDto
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@Api(tags = ["댓글 API"])
interface CommentContract {

    @ApiOperation("댓글 등록")
    @PostMapping(COMMENTS, produces = [MediaType.APPLICATION_JSON_VALUE])
    fun add(
        @PathVariable minutesId: Long,
        req: CommentDto.CreateReq
    ): SingleResult<CommentDto.CommentSimple>

    @ApiOperation("댓글 리스트")
    @GetMapping(COMMENTS, produces = [MediaType.APPLICATION_JSON_VALUE])
    fun listSimple(
        @PathVariable minutesId: Long
    ): ListResult<CommentDto.CommentSimple>

    @ApiOperation("댓글 상세 정보")
    @GetMapping("${COMMENTS}/{commentId}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getComment(
        @PathVariable minutesId: Long,
        @PathVariable commentId: Long
    ): SingleResult<CommentDto.CommentDetail>

    @ApiOperation("댓글 갱신")
    @PutMapping(COMMENTS, produces = [MediaType.APPLICATION_JSON_VALUE])
    fun update(
        @PathVariable minutesId: Long,
        req: CommentDto.UpdateReq
    ): DoneResult

    @ApiOperation("댓글 삭제")
    @DeleteMapping(COMMENTS, produces = [MediaType.APPLICATION_JSON_VALUE])
    fun delete(
        @PathVariable minutesId: Long
    ): DoneResult

}
package net.huray.backend.minuting.controller

import net.huray.backend.http.res.DoneResult
import net.huray.backend.http.res.ListResult
import net.huray.backend.http.res.SingleResult
import net.huray.backend.minuting.contract.CommentContract
import net.huray.backend.minuting.dto.CommentDto
import net.huray.backend.minuting.service.CommentService
import org.springframework.web.bind.annotation.RestController

@RestController
class CommentController(
    private val commentService: CommentService
) : CommentContract {

    override fun add(minutesId: Long, req: CommentDto.CreateReq) =
        SingleResult(commentService.add(minutesId, req))

    override fun listSimple(minutesId: Long) =
        ListResult(commentService.list(minutesId))

    override fun getComment(minutesId: Long, commentId: Long) =
        SingleResult(commentService.getComment(minutesId, commentId))

    override fun update(minutesId: Long, commentId: Long, req: CommentDto.UpdateReq): DoneResult {
        commentService.update(minutesId, commentId, req)

        return DoneResult()
    }

    override fun delete(minutesId: Long, commentId: Long): DoneResult {
        TODO("Not yet implemented")
    }

}
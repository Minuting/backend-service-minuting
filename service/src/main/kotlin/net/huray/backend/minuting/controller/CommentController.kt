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

    override fun list(minutesId: Long): ListResult<CommentDto.CommentSimple> {
        TODO("Not yet implemented")
    }

    override fun getComment(minutesId: Long, commentId: Long): SingleResult<CommentDto.CommentDetail> {
        TODO("Not yet implemented")
    }

    override fun update(minutesId: Long, req: CommentDto.UpdateReq): DoneResult {
        TODO("Not yet implemented")
    }

    override fun delete(minutesId: Long): DoneResult {
        TODO("Not yet implemented")
    }

}
package net.huray.backend.minuting.contract

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import net.huray.backend.http.res.SingleResult
import net.huray.backend.minuting.contract.Endpoint.COMMENTS
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping

@Api(tags = ["댓글 API"])
interface CommentContract {

    @ApiOperation("댓글 등록")
    @PostMapping(COMMENTS, produces = [MediaType.APPLICATION_JSON_VALUE])
    fun create(
        @PathVariable minutesId: Long
    )

}
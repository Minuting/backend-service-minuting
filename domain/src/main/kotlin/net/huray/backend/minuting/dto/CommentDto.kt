package net.huray.backend.minuting.dto

import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import springfox.documentation.annotations.ApiIgnore
import java.time.LocalDateTime

object CommentDto {

    @ApiIgnore
    open class BaseRes(
        @ApiModelProperty("댓글 ID")
        var commentId: Long,

        @ApiModelProperty("댓글 내용")
        var contents: String
    )

    @ApiModel("댓글 기본 응답 정보")
    open class SimpleRes(
        commentId: Long,
        contents: String
    ) : BaseRes(commentId, contents)

    @ApiModel("댓글 상세 응답 정보")
    class DetailRes(
        commentId: Long,
        contents: String
    ) : SimpleRes(commentId, contents) {

        @ApiModelProperty("댓글 생성 일자")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        var createdAt: LocalDateTime? = null

        @ApiModelProperty("댓글 최종 업데이트 일자")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        var updatedAt: LocalDateTime? = null

    }

    @ApiModel("댓글 등록 요청 정보")
    class CreateReq(
        @ApiModelProperty("댓글 내용")
        var contents: String
    )

    @ApiModel("댓글 수정 요청 정보")
    class UpdateReq(
        @ApiModelProperty("댓글 내용")
        var contents: String
    )

}
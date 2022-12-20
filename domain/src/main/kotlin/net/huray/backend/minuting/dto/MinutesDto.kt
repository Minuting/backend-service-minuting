package net.huray.backend.minuting.dto

import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDateTime

object MinutesDto {

    open class MinutesBase(
        @ApiModelProperty("회의록 ID", required = true)
        var id: Long,

        @ApiModelProperty("회의록 제목")
        var title: String,

        @ApiModelProperty("회의록 내용")
        var contents: String
    )

    open class MinutesSimple(
        id: Long,
        title: String,
        contents: String
    ) : MinutesBase(id, title, contents) {

    }

    open class MinutesDetail(
        id: Long,
        title: String,
        contents: String
    ) : MinutesSimple(id, title, contents) {

        @ApiModelProperty("회의록 생성 일자")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        var createdAt: LocalDateTime? = null

        @ApiModelProperty("회의록 최종 업데이트 일자")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        var updatedAt: LocalDateTime? = null
    }

    @ApiModel("회의록 등록 요청 정보")
    class CreateReq(
        @ApiModelProperty("회의록 제목")
        var title: String,

        @ApiModelProperty("회의록 내용")
        var contents: String
    )

    @ApiModel("회의록 수정 요청 정보")
    class UpdateReq(
        @ApiModelProperty("회의록 제목")
        var title: String,

        @ApiModelProperty("회의록 내용")
        var contents: String
    )

}
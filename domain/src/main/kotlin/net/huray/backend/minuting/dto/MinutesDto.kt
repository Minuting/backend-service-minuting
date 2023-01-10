package net.huray.backend.minuting.dto

import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import springfox.documentation.annotations.ApiIgnore
import java.time.LocalDateTime

object MinutesDto {

    @ApiIgnore
    open class BaseRes(
        @ApiModelProperty("회의록 ID", required = true)
        var id: Long,

        @ApiModelProperty("회의록 제목")
        var title: String,

        @ApiModelProperty("회의록 내용")
        var contents: String
    )

    @ApiModel("회의록 기본 응답 정보")
    open class SimpleRes(
        id: Long,
        title: String,
        contents: String
    ) : BaseRes(id, title, contents)

    @ApiModel("회의록 상세 응답 정보")
    open class DetailRes(
        id: Long,
        title: String,
        contents: String
    ) : SimpleRes(id, title, contents) {

        @ApiModelProperty("회의록 생성 일자")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        var createdAt: LocalDateTime? = null

        @ApiModelProperty("회의록 최종 업데이트 일자")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        var updatedAt: LocalDateTime? = null
    }

    @ApiModel("회의록 목록 요청 정보")
    class ListReq(
        @ApiModelProperty("회의록 제목")
        var title: String = "",

        @ApiModelProperty("회의록 내용")
        var contents: String = "",

        @ApiModelProperty("회의록 팀")
        var teamId: Long = 0L
    )

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
package net.huray.backend.minuting.dto

import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import springfox.documentation.annotations.ApiIgnore
import java.time.LocalDateTime

object TemplateDto {

    @ApiIgnore
    open class TemplateBase(
        @ApiModelProperty("템플릿 ID")
        var templateId: Long,

        @ApiModelProperty("템플릿 작성자 리스트")
        var user: List<Any>,

        @ApiModelProperty("템플릿 제목")
        var title: String,

        // TODO: 템플릿 저장 일단 String 값으로 함 -> 프론트랑 협업 필요
        @ApiModelProperty("템플릿 내용")
        var contents: String
    )

    @ApiModel("템플릿 기본 응답 정보")
    open class TemplateSimple(
        templateId: Long,
        user: List<Any>,
        title: String,
        contents: String
    ) : TemplateBase(templateId, user, title, contents) {

    }

    @ApiModel("템플릿 상세 응답 정보")
    class TemplateDetail(
        templateId: Long,
        user: List<Any>,
        title: String,
        contents: String
    ) : TemplateSimple(templateId, user, title, contents) {
        @ApiModelProperty("템플릿 생성 일자")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        var createdAt: LocalDateTime? = null

        @ApiModelProperty("템플릿 최종 업데이트 일자")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        var updatedAt: LocalDateTime? = null
    }

    @ApiModel("템플릿 리스트 요청 정보")
    class ListReq(
        @ApiModelProperty("템플릿 작성자")
        var user: Any
    )

    @ApiModel("템플릿 등록 요청 정보")
    class CreateReq(
        @ApiModelProperty("템플릿 제목")
        var title: String,

        @ApiModelProperty("템플릿 내용")
        var contents: String,

        // TODO: User Entity 및 Dto 정의 이후 수정
        @ApiModelProperty("템플릿 작성자")
        var user: Any
    )

    @ApiModel("템플릿 갱신 요청 정보")
    class UpdateReq(
        @ApiModelProperty("템플릿 제목")
        var title: String,

        @ApiModelProperty("템플릿 내용")
        var contents: String,

        // TODO: User Entity 및 Dto 정의 이후 수정
        @ApiModelProperty("템플릿 수정자")
        var user: Any
    )

}
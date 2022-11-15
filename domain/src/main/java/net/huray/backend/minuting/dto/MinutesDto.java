package net.huray.backend.minuting.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

//@ApiModel("회의록")
public class MinutesDto {

    @ApiModel("회의록 정보")
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MinutesSimple {
        @ApiModelProperty("회의록 ID")
        Long id;

        @ApiModelProperty("회의록 제목")
        String title;

        @ApiModelProperty("회의록 내용")
        String contents;
    }

    @ApiModel("회의록 상세 정보")
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MinutesDetail extends MinutesSimple {
        @ApiModelProperty("회의록 생성 일자")
        LocalDateTime createdAt;

        @ApiModelProperty("회의록 최종 업데이트 일자")
        LocalDateTime updatedAt;
    }

    @ApiModel("회의록 등록 요청 정보")
    @NoArgsConstructor
    @Data
    public static class CreateReq {
        @ApiModelProperty("회의록 제목")
        String title;

        @ApiModelProperty("회의록 내용")
        String contents;
    }

    @ApiModel("회의록 수정 요청 정보")
    @NoArgsConstructor
    @Data
    public static class UpdateReq {
        @ApiModelProperty("회의록 제목")
        String title;

        @ApiModelProperty("회의록 내용")
        String contents;
    }

}

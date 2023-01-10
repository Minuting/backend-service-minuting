package net.huray.backend.minuting.dto

import io.swagger.annotations.ApiModel

object CompanyDto {

    @ApiModel("회사 기본 응답 정보")
    class SimpleRes(
        val id: Long,
        val name: String,
        val ceo: String,
        val telNumber: String,
        val address: String
    )

}
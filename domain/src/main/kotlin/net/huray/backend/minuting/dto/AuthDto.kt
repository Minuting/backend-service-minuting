package net.huray.backend.minuting.dto

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

object AuthDto {

    @ApiModel("로그인 토큰 정보")
    class LoginRes(
        @ApiModelProperty("access token")
        val accessToken: String,

        @ApiModelProperty("refresh token")
        val refreshToken: String
    )

}
package net.huray.backend.minuting.dto

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

object AuthDto {

    @ApiModel("로그인 토큰 요청 정보")
    class LoginRes(
        @ApiModelProperty("Access Token")
        val accessToken: String,

        @ApiModelProperty("Refresh Token")
        val refreshToken: String
    )

    @ApiModel("토큰 리프레쉬 요청 정보")
    class TokenRefreshReq(
        @ApiModelProperty("Refresh Token")
        val refreshToken: String
    )

    @ApiModel("토큰 리프레쉬 반환 정보")
    class TokenRefreshRes(
        @ApiModelProperty("Access Token")
        val accessToken: String
    )

}
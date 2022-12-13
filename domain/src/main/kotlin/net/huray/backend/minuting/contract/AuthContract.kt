package net.huray.backend.minuting.contract

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import net.huray.backend.minuting.contract.Endpoint.AUTH
import net.huray.backend.minuting.dto.AuthDto.LoginRes
import net.huray.backend.minuting.dto.AuthDto.TokenRefreshReq
import net.huray.backend.minuting.dto.AuthDto.TokenRefreshRes
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam

@Api(tags = ["인증 API"])
interface AuthContract {

    @ApiOperation("인증 페이지로 이동")
    @GetMapping("$AUTH/code")
    fun moveUserGoogleCode(): String

    @ApiOperation("로그인")
    @PostMapping("$AUTH/login")
    fun login(@RequestParam("code") code: String): LoginRes

    @ApiOperation("토큰 리프레쉬")
    @PostMapping("$AUTH/token-refresh")
    fun tokenRefresh(@RequestBody req: TokenRefreshReq): TokenRefreshRes

}
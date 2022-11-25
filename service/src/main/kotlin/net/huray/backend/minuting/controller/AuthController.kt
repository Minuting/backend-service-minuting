package net.huray.backend.minuting.controller

import net.huray.backend.minuting.contract.AuthContract
import net.huray.backend.minuting.dto.AuthDto.LoginRes
import net.huray.backend.minuting.dto.AuthDto.TokenRefreshReq
import net.huray.backend.minuting.dto.AuthDto.TokenRefreshRes
import net.huray.backend.minuting.service.AuthService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class AuthController(
    private val authService: AuthService
): AuthContract {

    override fun moveUserGoogleCode(): String = "redirect:${authService.getUserGoogleCode()}"

    @ResponseBody
    override fun login(code: String): LoginRes = authService.login(code)

    @ResponseBody
    override fun tokenRefresh(req: TokenRefreshReq): TokenRefreshRes = authService.tokenRefresh(req)

}
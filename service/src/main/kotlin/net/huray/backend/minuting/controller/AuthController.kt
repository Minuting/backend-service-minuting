package net.huray.backend.minuting.controller

import net.huray.backend.minuting.contract.AuthContract
import net.huray.backend.minuting.service.AuthService
import org.springframework.stereotype.Controller

@Controller
class AuthController(
    private val authService: AuthService
): AuthContract {

    override fun moveUserGoogleCode(): String = "redirect:${authService.getUserGoogleCode()}"

}
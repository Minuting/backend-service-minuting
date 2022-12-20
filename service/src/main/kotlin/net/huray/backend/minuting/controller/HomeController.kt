package net.huray.backend.minuting.controller

import net.huray.backend.http.res.SingleResult
import net.huray.backend.minuting.service.UserService
import net.huray.backend.minuting.support.AuthenticationFacade
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class HomeController(
    private val userService: UserService,
    private val authenticationFacade: AuthenticationFacade
) {

    @GetMapping("/me")
    fun getMe() = SingleResult(userService.getBaseInfo(authenticationFacade.uid))

}
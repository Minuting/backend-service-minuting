package net.huray.backend.minuting.controller

import net.huray.backend.http.res.SingleResult
import net.huray.backend.minuting.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class HomeController(
    private val userService: UserService
) {

    @GetMapping("/me")
    fun getMe() =
        SingleResult(userService.getBaseInfo(UUID.fromString("55a03586-5f14-11ed-8e54-6cd5e80d8470")))

}
package net.huray.backend.minuting.controller

import net.huray.backend.http.res.ListResult
import net.huray.backend.minuting.contract.UserContract
import net.huray.backend.minuting.service.UserService
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val userService: UserService
) : UserContract {

    override fun listByName(name: String) =
        ListResult(userService.listByName(name))

}
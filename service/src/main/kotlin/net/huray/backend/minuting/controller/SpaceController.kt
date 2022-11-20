package net.huray.backend.minuting.controller

import net.huray.backend.http.res.SingleResult
import net.huray.backend.minuting.service.SpaceService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/space")
class SpaceController(
    private val spaceService: SpaceService
) {

    @GetMapping("/public")
    fun listPublicSpace() =
        SingleResult(spaceService.listPublicSpace(UUID.fromString("55a03586-5f14-11ed-8e54-6cd5e80d8470")))

}
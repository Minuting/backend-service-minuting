package net.huray.backend.minuting.controller

import net.huray.backend.http.res.ListResult
import net.huray.backend.minuting.contract.TagContract
import net.huray.backend.minuting.enums.TagType
import net.huray.backend.minuting.service.TagService
import org.springframework.web.bind.annotation.RestController

@RestController
class TagController(
    private val tagService: TagService
) : TagContract {

    override fun listByType(type: TagType) =
        ListResult(tagService.listByType(type))

}
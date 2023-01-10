package net.huray.backend.minuting.service

import net.huray.backend.minuting.dto.TagDto
import net.huray.backend.minuting.enums.TagType
import net.huray.backend.minuting.service.component.TagComponent
import org.springframework.stereotype.Service

@Service
class TagService(
    private val tagComponent: TagComponent
) {

    fun listByType(type: TagType) = tagComponent.listByType(type)
        .map { TagDto.SimpleRes(it.id, it.name, it.type, it.color, it.orderNum) }

}
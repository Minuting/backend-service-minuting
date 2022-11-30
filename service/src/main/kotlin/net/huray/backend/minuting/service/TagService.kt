package net.huray.backend.minuting.service

import net.huray.backend.minuting.dto.TagDto
import net.huray.backend.minuting.enums.TagType
import net.huray.backend.minuting.service.component.TagComponent
import org.springframework.stereotype.Service

@Service
class TagService(
    private val tagComponent: TagComponent
) {

    fun listByType(tagType: TagType) = tagComponent.listByType(tagType)
        .map {
            TagDto.TagSimple(it.id, it.name, it.type, it.color, it.orderNum)
        }

}
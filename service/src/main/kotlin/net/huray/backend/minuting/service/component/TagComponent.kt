package net.huray.backend.minuting.service.component

import net.huray.backend.minuting.enums.TagType
import net.huray.backend.minuting.repository.TagRepository
import org.springframework.stereotype.Component

@Component
class TagComponent(
    private val tagRepository: TagRepository
) {

    fun listByType(tagType: TagType) =
        tagRepository.findByType(tagType)

}
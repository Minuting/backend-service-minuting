package net.huray.backend.minuting.service.component

import net.huray.backend.http.exception.NotFoundException
import net.huray.backend.minuting.enums.TagType
import net.huray.backend.minuting.repository.TagRepository
import org.springframework.stereotype.Component

@Component
class TagComponent(
    private val tagRepository: TagRepository
) {

    fun get(id: Long) =
        tagRepository.findById(id).orElseThrow { throw NotFoundException("Not Found Tag (id:$id)") }

    fun listByType(tagType: TagType) =
        tagRepository.findByType(tagType)

}
package net.huray.backend.minuting.service.component

import net.huray.backend.http.exception.BaseException
import net.huray.backend.http.exception.code.ErrorCode.TAG_NOT_FOUND
import net.huray.backend.minuting.enums.TagType
import net.huray.backend.minuting.repository.TagRepository
import org.springframework.stereotype.Component

@Component
class TagComponent(
    private val tagRepository: TagRepository
) {

    fun get(id: Long) =
        tagRepository.findById(id).orElseThrow { throw BaseException(TAG_NOT_FOUND, id.toString()) }

    fun listByType(tagType: TagType) =
        tagRepository.findByType(tagType)

}
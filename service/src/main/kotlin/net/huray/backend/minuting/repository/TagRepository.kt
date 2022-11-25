package net.huray.backend.minuting.repository

import net.huray.backend.minuting.entity.TagEntity
import net.huray.backend.minuting.enums.TagType
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface TagRepository : JpaRepository<TagEntity, Long> {
    fun findByType(tagType: TagType): List<TagEntity>
}
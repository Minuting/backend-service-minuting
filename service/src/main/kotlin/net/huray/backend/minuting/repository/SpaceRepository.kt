package net.huray.backend.minuting.repository

import net.huray.backend.minuting.entity.SpaceEntity
import org.springframework.data.jpa.repository.JpaRepository

interface SpaceRepository : JpaRepository<SpaceEntity, Long> {

    fun findByIsPublicTrue(): List<SpaceEntity>

}
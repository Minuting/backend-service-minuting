package net.huray.backend.minuting.repository

import net.huray.backend.minuting.entity.MinutesEntity
import org.springframework.data.jpa.repository.JpaRepository

interface MinutesRepository : JpaRepository<MinutesEntity, Long> {
}
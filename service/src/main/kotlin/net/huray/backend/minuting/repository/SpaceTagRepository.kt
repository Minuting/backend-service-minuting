package net.huray.backend.minuting.repository

import net.huray.backend.minuting.entity.SpaceTagEntity
import org.springframework.data.jpa.repository.JpaRepository

interface SpaceTagRepository : JpaRepository<SpaceTagEntity, Long>
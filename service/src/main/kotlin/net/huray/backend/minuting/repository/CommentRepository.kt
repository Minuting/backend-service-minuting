package net.huray.backend.minuting.repository

import net.huray.backend.minuting.entity.CommentEntity
import net.huray.backend.minuting.entity.MinutesEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<CommentEntity, Long> {

    fun findAllByMinutes(minutesEntity: MinutesEntity): List<CommentEntity>

}
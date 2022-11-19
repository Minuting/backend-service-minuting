package net.huray.backend.minuting.repository

import net.huray.backend.minuting.entity.CommentEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<CommentEntity, Long> {
}
package net.huray.backend.minuting.service

import net.huray.backend.http.exception.NotFoundException
import net.huray.backend.minuting.dto.CommentDto
import net.huray.backend.minuting.entity.CommentEntity
import net.huray.backend.minuting.repository.CommentRepository
import net.huray.backend.minuting.repository.MinutesRepository
import net.huray.backend.minuting.support.ErrorMessages
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommentService(
    private val minutesRepository: MinutesRepository,
    private val commentRepository: CommentRepository
) {

    @Transactional
    fun add(minutesId: Long, req: CommentDto.CreateReq) =
        minutesRepository.findById(minutesId)
            .orElseThrow { throw NotFoundException(ErrorMessages.MINUTES_NOT_FOUND, minutesId) }
            .run {
                CommentEntity(req.contents, this)
                    .also {
                        addComment(it)
                        commentRepository.save(it)
                    }
            }
            .let { CommentDto.CommentSimple(it.id, it.contents) }

    fun list(minutesId: Long) = minutesRepository.findById(minutesId)
        .orElseThrow { throw NotFoundException(ErrorMessages.MINUTES_NOT_FOUND, minutesId) }
        .let { minutesEntity ->
            commentRepository.findAllByMinutes(minutesEntity)
                .map { CommentDto.CommentSimple(it.id, it.contents) }
        }

    fun getComment(minutesId: Long, commentId: Long) = minutesRepository.findById(minutesId)
        .orElseThrow { throw NotFoundException(ErrorMessages.MINUTES_NOT_FOUND, minutesId) }
        .let {
            commentRepository.findById(commentId)
                .orElseThrow { throw NotFoundException(ErrorMessages.COMMENT_NOT_FOUND, commentId) }
                .let {
                    CommentDto.CommentDetail(it.id, it.contents)
                        .apply { createdAt = it.createdAt; updatedAt = it.updatedAt }
                }
        }

    @Transactional
    fun update(minutesId: Long, commentId: Long, req: CommentDto.UpdateReq) {
        minutesRepository.findById(minutesId)
            .orElseThrow { throw NotFoundException(ErrorMessages.MINUTES_NOT_FOUND, minutesId) }
            .also {
                commentRepository.findById(commentId)
                    .orElseThrow { throw NotFoundException(ErrorMessages.COMMENT_NOT_FOUND, commentId) }
                    .apply { updateComment(req) }
            }
    }

    fun hardDelete(minutesId: Long, commentId: Long) {
        minutesRepository.findById(minutesId)
            .orElseThrow { throw NotFoundException(ErrorMessages.MINUTES_NOT_FOUND, minutesId) }
            .also {
                commentRepository.findById(commentId)
                    .orElseThrow { throw NotFoundException(ErrorMessages.COMMENT_NOT_FOUND, commentId) }
                    .also { commentRepository.delete(it) }
            }
    }

}
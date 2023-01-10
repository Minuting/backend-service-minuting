package net.huray.backend.minuting.service

import net.huray.backend.http.exception.BaseException
import net.huray.backend.http.exception.code.ErrorCode
import net.huray.backend.minuting.dto.MinutesDto
import net.huray.backend.minuting.entity.MinutesEntity
import net.huray.backend.minuting.repository.MinutesRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MinutesService(
    private val minutesRepository: MinutesRepository
) {

    fun list() = minutesRepository.findAll()
        .map { MinutesDto.SimpleRes(it.id, it.title, it.contents) }

    fun getDetailById(id: Long) = minutesRepository.findById(id)
        .orElseThrow { throw BaseException(ErrorCode.MINUTES_NOT_FOUND, id) }
        .let {
            MinutesDto.DetailRes(it.id, it.title, it.contents).apply {
                this.createdAt = it.createdAt
                this.updatedAt = it.updatedAt
            }
        }

    @Transactional
    fun create(req: MinutesDto.CreateReq) = minutesRepository.save(MinutesEntity(req.title, req.contents))
        .let { MinutesDto.SimpleRes(it.id, it.title, it.contents) }

    @Transactional
    fun update(id: Long, req: MinutesDto.UpdateReq) = minutesRepository.findById(id)
        .orElseThrow { throw Exception() }
        .run { updateMinutes(req.title, req.contents) }

    @Transactional
    fun hardDelete(id: Long) {
        minutesRepository.deleteById(id)
    }

}
package net.huray.backend.minuting.service

import net.huray.backend.http.exception.BaseException
import net.huray.backend.http.exception.code.ErrorCode
import net.huray.backend.minuting.dto.MinutesDto
import net.huray.backend.minuting.repository.MinutesRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MinutesReadService(
    private val minutesRepository: MinutesRepository
) {

    fun list() = minutesRepository.findAll()
        .map { MinutesDto.MinutesSimple(it.id, it.title, it.contents) }

    fun getDetailById(id: Long) = minutesRepository.findById(id)
        .orElseThrow { throw BaseException(ErrorCode.MINUTES_NOT_FOUND, id) }
        .let {
            MinutesDto.MinutesDetail(it.id, it.title, it.contents).apply {
                this.createdAt = it.createdAt
                this.updatedAt = it.updatedAt
            }
        }

}
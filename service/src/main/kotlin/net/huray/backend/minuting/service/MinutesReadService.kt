package net.huray.backend.minuting.service

import net.huray.backend.http.exception.NotFoundException
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
        .orElseThrow { throw NotFoundException("Not Found Minutes (id:$id)") }
        .let {
            MinutesDto.MinutesDetail(it.id, it.title, it.contents).apply {
                this.createdAt = it.createdAt
                this.updatedAt = it.updatedAt
            }
        }

}
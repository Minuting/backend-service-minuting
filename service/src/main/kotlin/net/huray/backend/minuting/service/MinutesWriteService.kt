package net.huray.backend.minuting.service

import net.huray.backend.minuting.dto.MinutesDto
import net.huray.backend.minuting.entity.MinutesEntity
import net.huray.backend.minuting.repository.MinutesRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class MinutesWriteService(
    private val minutesRepository: MinutesRepository
) {

    fun create(req: MinutesDto.CreateReq) = minutesRepository.save(MinutesEntity(req.title, req.contents))
        .let { MinutesDto.MinutesSimple(it.id, it.title, it.contents) }

    fun update(id: Long, req: MinutesDto.UpdateReq) = minutesRepository.findById(id)
        .orElseThrow { throw Exception() }
        .run { updateMinutes(req.title, req.contents) }

    fun hardDelete(id: Long) = minutesRepository.deleteById(id)

}
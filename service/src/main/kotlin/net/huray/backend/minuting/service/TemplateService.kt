package net.huray.backend.minuting.service

import net.huray.backend.http.exception.BaseException
import net.huray.backend.http.exception.code.ErrorCode.TEMPLATE_NOT_FOUND
import net.huray.backend.minuting.dto.TemplateDto
import net.huray.backend.minuting.entity.TemplateEntity
import net.huray.backend.minuting.repository.TemplateRepository
import net.huray.backend.minuting.service.component.UserComponent
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service

class TemplateService(
    private val userComponent: UserComponent,
    private val templateRepository: TemplateRepository
) {

    @Transactional
    fun create(req: TemplateDto.CreateReq): TemplateDto.TemplateSimple {
        val user = userComponent.get(req.userId)

        return TemplateEntity(req.title, req.contents, user)
            .also { templateRepository.save(it) }
            .toTemplateSimple()
    }

    @Transactional(readOnly = true)
    fun list() = templateRepository.findAll()
        .map { it.toTemplateSimple() }

    @Transactional(readOnly = true)
    fun get(templateId: Long) = templateRepository.findById(templateId)
        .orElseThrow { throw BaseException(TEMPLATE_NOT_FOUND, templateId.toString()) }
        .toTemplateDetail()

    @Transactional
    fun update(templateId: Long, req: TemplateDto.UpdateReq) = templateRepository.findById(templateId)
        .orElseThrow { throw BaseException(TEMPLATE_NOT_FOUND, templateId.toString()) }
        .updateTemplate(req.title, req.contents, userComponent.get(req.userId))

    @Transactional
    fun hardDelete(templateId: Long) {
        templateRepository.findById(templateId)
            .orElseThrow { throw BaseException(TEMPLATE_NOT_FOUND, templateId.toString()) }
            .also { templateRepository.delete(it) }
    }

    // TODO: 유저 회원정보가 완료되면 User 리턴값 수정
    private fun TemplateEntity.toTemplateSimple() =
        TemplateDto.TemplateSimple(id, null, title, contents)

    private fun TemplateEntity.toTemplateDetail() =
        TemplateDto.TemplateDetail(id, null, title, contents)
            .also { it.createdAt = this.createdAt; it.updatedAt = this.updatedAt }

}
package net.huray.backend.minuting.controller

import net.huray.backend.http.res.DoneResult
import net.huray.backend.http.res.ListResult
import net.huray.backend.http.res.SingleResult
import net.huray.backend.minuting.contract.TemplateContract
import net.huray.backend.minuting.dto.TemplateDto
import net.huray.backend.minuting.service.TemplateService
import org.springframework.web.bind.annotation.RestController

@RestController
class TemplateController(
    private val templateService: TemplateService
) : TemplateContract {

    override fun create(req: TemplateDto.CreateReq) =
        SingleResult(templateService.create(req))

    override fun listSimple() =
        ListResult(templateService.list());

    override fun getDetail(templateId: Long) =
        SingleResult(templateService.get(templateId))

    override fun update(templateId: Long, req: TemplateDto.UpdateReq): DoneResult {
        templateService.update(templateId, req)

        return DoneResult()
    }

    override fun delete(templateId: Long): DoneResult {
        templateService.hardDelete(templateId)

        return DoneResult()
    }

}
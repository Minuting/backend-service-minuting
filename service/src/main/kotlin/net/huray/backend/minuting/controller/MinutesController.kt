package net.huray.backend.minuting.controller

import net.huray.backend.http.res.DoneResult
import net.huray.backend.http.res.ListResult
import net.huray.backend.http.res.SingleResult
import net.huray.backend.minuting.contract.MinutesContract
import net.huray.backend.minuting.dto.MinutesDto
import net.huray.backend.minuting.service.MinutesService
import org.springframework.web.bind.annotation.RestController

@RestController
class MinutesController(
    private val minutesService: MinutesService
) : MinutesContract {

    override fun create(req: MinutesDto.CreateReq) =
        SingleResult(minutesService.create(req))

    override fun listSimple(req: MinutesDto.ListReq) =
        ListResult(minutesService.list())

    override fun getDetail(minutesId: Long) =
        SingleResult(minutesService.getDetailById(minutesId))

    override fun update(minutesId: Long, req: MinutesDto.UpdateReq): DoneResult {
        minutesService.update(minutesId, req)

        return DoneResult()
    }

    override fun delete(minutesId: Long): DoneResult {
        minutesService.hardDelete(minutesId)

        return DoneResult()
    }

}
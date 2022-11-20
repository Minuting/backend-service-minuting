package net.huray.backend.minuting.controller

import net.huray.backend.http.res.ListResult
import net.huray.backend.http.res.SingleResult
import net.huray.backend.minuting.contract.MinutesContract
import net.huray.backend.minuting.dto.MinutesDto
import net.huray.backend.minuting.service.MinutesReadService
import net.huray.backend.minuting.service.MinutesWriteService
import org.springframework.web.bind.annotation.RestController

@RestController
class MinutesController(
    private val minutesReadService: MinutesReadService,
    private val minutesWriteService: MinutesWriteService
) : MinutesContract {

    override fun create(req: MinutesDto.CreateReq) = SingleResult(minutesWriteService.create(req))

    override fun listSimple() = ListResult(minutesReadService.list())

    override fun getDetail(id: Long) = SingleResult(minutesReadService.getDetailById(id))

    override fun update(
        id: Long,
        req: MinutesDto.UpdateReq
    ) = minutesWriteService.update(id, req)

    override fun delete(id: Long) = minutesWriteService.hardDelete(id)

}
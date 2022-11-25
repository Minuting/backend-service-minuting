package net.huray.backend.minuting.controller

import net.huray.backend.http.res.ListResult
import net.huray.backend.http.res.SingleResult
import net.huray.backend.minuting.contract.SpaceContract
import net.huray.backend.minuting.dto.SpaceDto
import net.huray.backend.minuting.service.SpaceService
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class SpaceController(
    private val spaceService: SpaceService
) : SpaceContract {

    override fun create(req: SpaceDto.CreateReq): SingleResult<SpaceDto.SpaceSimple> =
        SingleResult(spaceService.create(UUID.fromString("55a03586-5f14-11ed-8e54-6cd5e80d8470"), req))

    override fun get(id: Long): SingleResult<SpaceDto.SpaceDetail> =
        SingleResult(spaceService.get(UUID.fromString("55a03586-5f14-11ed-8e54-6cd5e80d8470"), id))

    override fun listPublic(): ListResult<SpaceDto.SpacePublic> =
        ListResult(spaceService.listPublic(UUID.fromString("55a03586-5f14-11ed-8e54-6cd5e80d8470")))

    override fun update(id: Long, req: SpaceDto.UpdateReq) =
        SingleResult(spaceService.update(UUID.fromString("55a03586-5f14-11ed-8e54-6cd5e80d8470"), id, req))

    override fun delete(id: Long) {
        spaceService.delete(UUID.fromString("55a03586-5f14-11ed-8e54-6cd5e80d8470"), id);
    }

    override fun join(id: Long) {
        SingleResult(spaceService.join(UUID.fromString("55a03586-5f14-11ed-8e54-6cd5e80d8470"), id))
    }
}
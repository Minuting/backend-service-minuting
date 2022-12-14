package net.huray.backend.minuting.controller

import net.huray.backend.http.res.DoneResult
import net.huray.backend.http.res.ListResult
import net.huray.backend.http.res.SingleResult
import net.huray.backend.minuting.contract.SpaceContract
import net.huray.backend.minuting.dto.SpaceDto
import net.huray.backend.minuting.service.SpaceService
import net.huray.backend.minuting.support.AuthenticationFacade
import org.springframework.web.bind.annotation.RestController

@RestController
class SpaceController(
    private val spaceService: SpaceService,
    private val authenticationFacade: AuthenticationFacade
) : SpaceContract {

    override fun create(req: SpaceDto.CreateReq) =
        SingleResult(spaceService.create(authenticationFacade.uid, req))

    override fun get(id: Long) =
        SingleResult(spaceService.get(authenticationFacade.uid, id))

    override fun listPublic() =
        ListResult(spaceService.listPublic(authenticationFacade.uid))

    override fun update(id: Long, req: SpaceDto.UpdateReq): DoneResult {
        spaceService.update(authenticationFacade.uid, id, req)

        return DoneResult()
    }

    override fun delete(id: Long) {
        spaceService.delete(authenticationFacade.uid, id)
    }

    override fun join(id: Long) {
        spaceService.join(authenticationFacade.uid, id)
    }

}
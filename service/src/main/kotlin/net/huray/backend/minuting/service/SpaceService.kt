package net.huray.backend.minuting.service

import net.huray.backend.minuting.dto.Res
import net.huray.backend.minuting.service.component.SpaceComponent
import net.huray.backend.minuting.service.component.UserComponent
import org.springframework.stereotype.Service
import java.util.*

@Service
class SpaceService(
    private val userComponent: UserComponent,
    private val spaceComponent: SpaceComponent
) {

    fun listPublicSpace(uid: UUID) = userComponent.get(uid)
        .let { memberEntity ->
            spaceComponent.listPermissionByMember(memberEntity)
                .map { it.space }
                .filter { it.isPublic }
        }.let { spaceEntityList ->
            spaceComponent.listPublicSpace()
                .map { publicSpace ->
                    Res.PublicSpaceRes(
                        publicSpace.id,
                        publicSpace.name,
                        publicSpace.icon,
                        publicSpace.isPublic
                    ).also {
                        it.isJoined = spaceEntityList.any { space -> space.id == publicSpace.id }
                    }

                }
        }

}
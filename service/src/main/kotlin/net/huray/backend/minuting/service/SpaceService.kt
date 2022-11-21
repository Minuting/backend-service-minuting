package net.huray.backend.minuting.service

import net.huray.backend.minuting.dto.SpaceDto
import net.huray.backend.minuting.entity.MemberEntity
import net.huray.backend.minuting.enums.SpacePermissionType
import net.huray.backend.minuting.service.component.MinutesComponent
import net.huray.backend.minuting.service.component.SpaceComponent
import net.huray.backend.minuting.service.component.UserComponent
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class SpaceService(
    private val userComponent: UserComponent,
    private val spaceComponent: SpaceComponent,
    private val minutesComponent: MinutesComponent
) {
    fun get(uid: UUID, id: Long) = spaceComponent.get(id)
            .orElseThrow()
            .let { spaceEntity ->
                    var type = if (spaceEntity.ownerId.equals(uid)) {
                        SpacePermissionType.OWNER
                    } else {
                        var permission = spaceComponent.getPermissionBySpaceAndMember(spaceEntity, MemberEntity(uid));
                        if (permission.isPresent) {
                            permission.get().type
                        } else {
                            if (minutesComponent.listAttendeeMemberAndSpace(MemberEntity(uid), spaceEntity).isEmpty())
                                SpacePermissionType.GUEST
                            else
                                throw Error()
                        }
                    }
                    SpaceDto.SpaceDetail(
                            spaceEntity.id,
                            spaceEntity.name,
                            spaceEntity.icon,
                            spaceEntity.isPublic,

                    ).also {
                        it.spacePermissionType = type as SpacePermissionType
                    }
                }
    fun listPublic(uid: UUID) = userComponent.get(uid)
        .let { memberEntity ->
            spaceComponent.listPermissionByMember(memberEntity)
                .map { it.space }
                .filter { it.isPublic }
        }.let { spaceEntityList ->
            spaceComponent.listPublic()
                .map { publicSpace ->
                    SpaceDto.SpacePublic(
                        publicSpace.id,
                        publicSpace.name,
                        publicSpace.icon,
                        publicSpace.isPublic
                    ).also {
                        it.isJoined = spaceEntityList.any { space -> space.id == publicSpace.id }
                    }
                }
        }


//    @Transactional
//    fun createSpace() = spaceComponent.save(null);

}
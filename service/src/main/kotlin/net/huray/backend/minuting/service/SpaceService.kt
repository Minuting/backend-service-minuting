package net.huray.backend.minuting.service

import net.huray.backend.http.exception.ConflictException
import net.huray.backend.http.exception.ForbiddenException
import net.huray.backend.http.exception.NotFoundException
import net.huray.backend.minuting.dto.SpaceDto
import net.huray.backend.minuting.entity.MemberEntity
import net.huray.backend.minuting.entity.PermissionEntity
import net.huray.backend.minuting.entity.SpaceEntity
import net.huray.backend.minuting.enums.MemberType
import net.huray.backend.minuting.enums.PermissionType
import net.huray.backend.minuting.enums.SpacePermissionType
import net.huray.backend.minuting.service.component.MinutesComponent
import net.huray.backend.minuting.service.component.SpaceComponent
import net.huray.backend.minuting.service.component.UserComponent
import net.huray.backend.minuting.support.ErrorMessages
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
        .run {
            if (ownerId == uid) SpacePermissionType.OWNER
            else {
                spaceComponent.getPermissionBySpaceAndMember(this, MemberEntity(uid))?.type ?: SpacePermissionType.GUEST
            }.let { SpaceDto.SpaceDetail(id, name, description, icon, isPublic, it as SpacePermissionType) }
        }

    fun listPublic(uid: UUID) = userComponent.get(uid)
        .let { memberEntity ->
            spaceComponent.listPermissionByMember(memberEntity)
                .map { it.space }
                .filter { it!!.isPublic }
        }.let { spaceEntityList ->
            spaceComponent.listPublic()
                .map { publicSpace ->
                    SpaceDto.SpacePublic(
                        publicSpace.id,
                        publicSpace.name,
                        publicSpace.description,
                        publicSpace.icon,
                        publicSpace.isPublic
                    ).also {
                        it.isJoined = spaceEntityList.any { space -> space!!.id == publicSpace.id }
                    }
                }
        }

    @Transactional
    fun create(uid: UUID, req: SpaceDto.CreateReq) =
        spaceComponent.save(SpaceEntity(req.name, req.description, req.icon, uid, req.isPublic))
            .let { spaceEntity ->
                spaceComponent.savePermissionAll(req.permissions.map {
                    PermissionEntity(it.memberId, it.type, spaceEntity.id)
                }.toMutableList())
                SpaceDto.SpaceSimple(
                    spaceEntity.id,
                    spaceEntity.name,
                    spaceEntity.description,
                    spaceEntity.icon,
                    spaceEntity.isPublic,
                )
            }


    @Transactional
    fun update(uid: UUID, id: Long, req: SpaceDto.UpdateReq) =
        spaceComponent.get(id)
            .let { spaceEntity ->
                spaceEntity.updateSpace(
                    req.name,
                    req.description,
                    req.icon,
                    req.isPublic,
                    req.permissions.map {
                        PermissionEntity(it.memberId, it.type, spaceEntity.id)
                    }.toMutableList()
                )
                SpaceDto.SpaceSimple(
                    spaceEntity.id,
                    spaceEntity.name,
                    spaceEntity.description,
                    spaceEntity.icon,
                    spaceEntity.isPublic,
                )
            }

    @Transactional
    fun delete(uid: UUID, id: Long) =
        spaceComponent.get(id)
            .let { spaceEntity ->
                if (uid != spaceEntity.ownerId || MemberType.ADMIN == userComponent.get(uid).memberType)
                    throw ForbiddenException(ErrorMessages.SPACE_FORBIDDEN, id)
                spaceComponent.delete(id)
            }


    @Transactional
    fun join(uid: UUID, id: Long) =
        spaceComponent.get(id)
            .let { spaceEntity ->
                if (!spaceEntity.isPublic)
                    throw ForbiddenException(ErrorMessages.SPACE_FORBIDDEN, id)
                if (spaceEntity.permissions.any { e -> e.memberId == uid })
                    throw ConflictException(ErrorMessages.SPACE_ALREADY_JOINED, id)
                spaceEntity.permissions.add( PermissionEntity(uid, PermissionType.WRITE, spaceEntity.id))
            }
}
package net.huray.backend.minuting.service

import net.huray.backend.http.exception.ConflictException
import net.huray.backend.http.exception.ForbiddenException
import net.huray.backend.minuting.dto.SpaceDto
import net.huray.backend.minuting.dto.TagDto

import net.huray.backend.minuting.entity.PermissionEntity
import net.huray.backend.minuting.entity.SpaceEntity
import net.huray.backend.minuting.entity.SpaceTagEntity
import net.huray.backend.minuting.enums.MemberType
import net.huray.backend.minuting.enums.PermissionType
import net.huray.backend.minuting.enums.SpacePermissionType
import net.huray.backend.minuting.service.component.MinutesComponent
import net.huray.backend.minuting.service.component.SpaceComponent
import net.huray.backend.minuting.service.component.TagComponent
import net.huray.backend.minuting.service.component.UserComponent
import net.huray.backend.minuting.support.ErrorMessages
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class SpaceService(
    private val userComponent: UserComponent,
    private val spaceComponent: SpaceComponent,
    private val minutesComponent: MinutesComponent,
    private val tagComponent: TagComponent
) {
    fun get(uid: UUID, id: Long) = spaceComponent.get(id)
        .run {
            if (owner.uid == uid) SpacePermissionType.OWNER
            else {
                spaceComponent.getPermissionBySpaceAndMember(this, userComponent.get(uid))?.type
                    ?: SpacePermissionType.GUEST
            }.let {
                SpaceDto.SpaceDetail(
                    id,
                    name,
                    description,
                    icon,
                    isPublic,
                    it as SpacePermissionType,
                    permissions.map { SpaceDto.SpaceMemberBase(it.member.name, it.type) },
                    spaceTags.map {
                        var tag = it.tag
                        TagDto.TagSimple(tag.id, tag.name, tag.type, tag.color, tag.orderNum)
                    })
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
                        publicSpace.description,
                        publicSpace.icon,
                        publicSpace.isPublic
                    ).also {
                        it.isJoined = spaceEntityList.any { space -> space.id == publicSpace.id }
                    }
                }
        }

    @Transactional
    fun create(uid: UUID, req: SpaceDto.CreateReq) =
        spaceComponent.save(
            SpaceEntity(
                req.name,
                req.description,
                req.icon,
                userComponent.get(uid),
                req.isPublic,
            )
        ).let { spaceEntity ->
            spaceComponent.saveSpaceTagAll(
                req.tagIdList.map {
                    SpaceTagEntity(
                        spaceEntity,
                        tagComponent.get(it)
                    )
                }.toMutableList()
            )
            spaceComponent.savePermissionAll(req.permissions.map {
                PermissionEntity(userComponent.get(it.memberId), it.type, spaceEntity)
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
                    req.tagIdList.map {
                        SpaceTagEntity(
                            spaceEntity,
                            tagComponent.get(it)
                        )
                    }.toMutableList(),
                    req.permissions.map {
                        PermissionEntity(userComponent.get(it.memberId), it.type, spaceEntity)
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
                if (uid != spaceEntity.owner.uid || MemberType.ADMIN == userComponent.get(uid).memberType)
                    throw ForbiddenException(ErrorMessages.SPACE_FORBIDDEN, id)
                spaceComponent.delete(id)
            }


    @Transactional
    fun join(uid: UUID, id: Long) =
        spaceComponent.get(id)
            .let { spaceEntity ->
                if (!spaceEntity.isPublic)
                    throw ForbiddenException(ErrorMessages.SPACE_FORBIDDEN, id)
                if (spaceEntity.permissions.any { e -> e.member.uid == uid })
                    throw ConflictException(ErrorMessages.SPACE_ALREADY_JOINED, id)
                spaceEntity.permissions.add(
                    PermissionEntity(
                        userComponent.get(uid),
                        PermissionType.WRITE,
                        spaceEntity
                    )
                )
            }
}
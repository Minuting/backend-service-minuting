package net.huray.backend.minuting.service

import net.huray.backend.http.exception.BaseException
import net.huray.backend.http.exception.code.ErrorCode.SPACE_FORBIDDEN
import net.huray.backend.minuting.dto.SpaceDto
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
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional(readOnly = true)
class SpaceService(
    private val userComponent: UserComponent,
    private val spaceComponent: SpaceComponent,
    private val minutesComponent: MinutesComponent,
    private val tagComponent: TagComponent
) {

    fun get(uid: UUID, id: Long) = spaceComponent.get(id)
        .let {
            val permission = if (it.owner.uid == uid) SpacePermissionType.OWNER
            else spaceComponent.getPermissionBySpaceAndMember(it, userComponent.get(uid))?.type
                ?: SpacePermissionType.GUEST

            SpaceDto.DetailRes(it, permission as SpacePermissionType, it.permissionList, it.spaceTagList)
        }

    fun listPublic(uid: UUID) = with(userComponent.get(uid)) {
        spaceComponent.listPermissionByMember(this)
            .map { it.space }
            .filter { it.isPublic }
            .let { list -> spaceComponent.listPublic().map { SpaceDto.PublicRes(it, list) } }
    }

    @Transactional
    fun create(uid: UUID, req: SpaceDto.CreateReq) =
        spaceComponent.save(
            SpaceEntity(req.name, req.description, req.icon, req.isPublic, userComponent.get(uid))
        ).also { space ->
            spaceComponent.saveSpaceTagAll(
                req.tagIdList.map { SpaceTagEntity(space, tagComponent.get(it)) }
            )
            spaceComponent.savePermissionAll(
                req.permissionList.map { PermissionEntity(it.type, userComponent.get(it.memberId), space) }
            )
        }.let { SpaceDto.SimpleRes(it) }


    @Transactional
    fun update(uid: UUID, id: Long, req: SpaceDto.UpdateReq) =
        spaceComponent.get(id)
            .run {
                updateSpace(name, description, icon, isPublic)
                addAllSpaceTags(req.tagIdList.map { SpaceTagEntity(this, tagComponent.get(it)) })
                addAllPermissions(
                    req.permissionList.map { PermissionEntity(it.type, userComponent.get(it.memberId), this) }
                )

                SpaceDto.SimpleRes(this)
            }

    @Transactional
    fun delete(uid: UUID, id: Long) {
        spaceComponent.get(id)
            .also {
                if (uid != it.owner.uid || userComponent.get(uid).memberType == MemberType.ADMIN)
                    throw BaseException(SPACE_FORBIDDEN, id)
            }.let { spaceComponent.delete(id) }
    }


    @Transactional
    fun join(uid: UUID, id: Long) {
        spaceComponent.get(id)
            .apply {
                if (!isPublic) throw BaseException(SPACE_FORBIDDEN, id)
                if (permissionList.any { it.member.uid == uid }) throw BaseException(SPACE_FORBIDDEN, id)

                addAllPermissions(listOf(PermissionEntity(PermissionType.WRITE, userComponent.get(uid), this)))
            }
    }

}
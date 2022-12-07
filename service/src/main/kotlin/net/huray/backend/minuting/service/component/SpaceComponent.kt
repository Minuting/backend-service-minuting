package net.huray.backend.minuting.service.component

import net.huray.backend.http.exception.BaseException
import net.huray.backend.http.exception.code.ErrorCode.SPACE_NOT_FOUND
import net.huray.backend.minuting.entity.MemberEntity
import net.huray.backend.minuting.entity.PermissionEntity
import net.huray.backend.minuting.entity.SpaceEntity
import net.huray.backend.minuting.entity.SpaceTagEntity
import net.huray.backend.minuting.repository.PermissionRepository
import net.huray.backend.minuting.repository.SpaceRepository
import net.huray.backend.minuting.repository.SpaceTagRepository
import org.springframework.stereotype.Component

@Component
class SpaceComponent(
    private val spaceRepository: SpaceRepository,
    private val permissionRepository: PermissionRepository,
    private val spaceTagRepository: SpaceTagRepository
) {

    fun get(id: Long) =
        spaceRepository.findById(id)
            .orElseThrow { throw BaseException(SPACE_NOT_FOUND, id) }

    fun listPermissionByMember(member: MemberEntity) =
        permissionRepository.findByMember(member)

    fun getPermissionBySpaceAndMember(space: SpaceEntity, member: MemberEntity) =
        permissionRepository.findBySpaceAndMember(space, member)

    fun listPublic() = spaceRepository.findByIsPublicTrue()

    fun save(space: SpaceEntity) = spaceRepository.saveAndFlush(space)

    fun savePermissionAll(permissions: List<PermissionEntity>) =
        permissionRepository.saveAll(permissions)

    fun saveSpaceTagAll(spaceTags: List<SpaceTagEntity>) =
        spaceTagRepository.saveAll(spaceTags)

    fun delete(id: Long) =
        spaceRepository.deleteById(id)

}
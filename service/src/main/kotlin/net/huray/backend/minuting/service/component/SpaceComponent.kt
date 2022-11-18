package net.huray.backend.minuting.service.component

import net.huray.backend.minuting.entity.MemberEntity
import net.huray.backend.minuting.repository.PermissionRepository
import net.huray.backend.minuting.repository.SpaceRepository
import org.springframework.stereotype.Component

@Component
class SpaceComponent(
    private val spaceRepository: SpaceRepository,
    private val permissionRepository: PermissionRepository
) {

    fun listPermissionByMember(member: MemberEntity) =
        permissionRepository.findDistinctByMemberOrTeam(member, member.team)

    fun listPublicSpace() = spaceRepository.findByIsPublicTrue()

}
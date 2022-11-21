package net.huray.backend.minuting.repository

import net.huray.backend.minuting.entity.MemberEntity
import net.huray.backend.minuting.entity.PermissionEntity
import net.huray.backend.minuting.entity.TeamEntity
import org.springframework.data.jpa.repository.JpaRepository

interface PermissionRepository : JpaRepository<PermissionEntity, Long> {

    fun findDistinctByMemberOrTeam(member: MemberEntity, team: TeamEntity): List<PermissionEntity>

}
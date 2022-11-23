package net.huray.backend.minuting.repository

import net.huray.backend.minuting.entity.MemberEntity
import net.huray.backend.minuting.entity.PermissionEntity
import net.huray.backend.minuting.entity.SpaceEntity
import net.huray.backend.minuting.entity.TeamEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.lang.annotation.Native
import java.util.*

interface PermissionRepository : JpaRepository<PermissionEntity, Long> {

    fun findByMember(member: MemberEntity): List<PermissionEntity>
    fun findBySpaceAndMember(space: SpaceEntity,member: MemberEntity): PermissionEntity?
}
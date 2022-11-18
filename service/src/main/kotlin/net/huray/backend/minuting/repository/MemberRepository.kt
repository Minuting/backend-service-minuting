package net.huray.backend.minuting.repository

import net.huray.backend.minuting.entity.MemberEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface MemberRepository : JpaRepository<MemberEntity, UUID> {
}
package net.huray.backend.minuting.repository

import net.huray.backend.minuting.entity.MemberEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface MemberRepository : JpaRepository<MemberEntity, UUID> {

    fun findByNameContains(name: String): List<MemberEntity>

    fun findByEmail(email: String): MemberEntity

}
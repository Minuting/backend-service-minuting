package net.huray.backend.minuting.repository

import net.huray.backend.minuting.entity.AccountEntity
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository

interface AccountRepository : JpaRepository<AccountEntity, Long> {

    fun findByEmail(email: String): AccountEntity?

    @EntityGraph(attributePaths = ["memberEntity"])
    fun findWithMemberById(id: Long): AccountEntity?

}
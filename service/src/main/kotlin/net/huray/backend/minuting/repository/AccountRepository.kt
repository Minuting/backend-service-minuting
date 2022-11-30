package net.huray.backend.minuting.repository

import net.huray.backend.minuting.entity.AccountEntity
import org.springframework.data.jpa.repository.JpaRepository

interface AccountRepository : JpaRepository<AccountEntity, Long> {

    fun findByEmail(email: String): AccountEntity?
}
package net.huray.backend.minuting.repository

import net.huray.backend.minuting.entity.CompanyEntity
import net.huray.backend.minuting.entity.TeamEntity
import org.springframework.data.jpa.repository.JpaRepository

interface TeamRepository : JpaRepository<TeamEntity, Long> {

    fun findByCompany(companyEntity: CompanyEntity): List<TeamEntity>

}
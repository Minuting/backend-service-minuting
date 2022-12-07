package net.huray.backend.minuting.service.component

import net.huray.backend.minuting.entity.CompanyEntity
import net.huray.backend.minuting.repository.TeamRepository
import org.springframework.stereotype.Component

@Component
class CompanyComponent(
    private val teamRepository: TeamRepository
) {
    fun listTeamByCompany(companyEntity: CompanyEntity) =
        teamRepository.findByCompany(companyEntity)
}
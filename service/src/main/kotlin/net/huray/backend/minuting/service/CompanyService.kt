package net.huray.backend.minuting.service

import net.huray.backend.minuting.dto.TeamDto
import net.huray.backend.minuting.service.component.CompanyComponent
import net.huray.backend.minuting.service.component.UserComponent
import net.huray.backend.minuting.support.AuthenticationFacade
import org.springframework.stereotype.Service

@Service
class CompanyService(
    private val companyComponent: CompanyComponent,
    private val userComponent: UserComponent,
    private val authenticationFacade: AuthenticationFacade
) {
    fun listTeam() = authenticationFacade.uid?.let {
        userComponent.get(it)
            .let {
                companyComponent.listTeamByCompany(it.company)
            }.map {
                TeamDto.TeamSimpleDto(it.id, it.name, it.pId)
            }
    }
}
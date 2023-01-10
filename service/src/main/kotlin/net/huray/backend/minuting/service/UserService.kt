package net.huray.backend.minuting.service

import net.huray.backend.minuting.dto.CompanyDto
import net.huray.backend.minuting.dto.SpaceDto
import net.huray.backend.minuting.dto.TeamDto
import net.huray.backend.minuting.dto.UserInfoDto
import net.huray.backend.minuting.service.component.SpaceComponent
import net.huray.backend.minuting.service.component.UserComponent
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(
    private val userComponent: UserComponent,
    private val spaceComponent: SpaceComponent
) {

    fun getBaseInfo(uid: UUID) = userComponent.get(uid)
        .run {
            UserInfoDto.UserInfoDetail(
                name, memberType,
                CompanyDto.SimpleRes(company.id, company.name, company.ceo, company.telNumber, company.address),
                TeamDto.SimpleRes(id = team.id, name = team.name),
                spaceComponent.listPermissionByMember(this).map { SpaceDto.SimpleRes(it.space) }
            )
        }

    fun listByName(name: String) = userComponent.searchByName(name)
        .map {
            UserInfoDto.UserInfoSimple(it.uid, it.name)
        }

}
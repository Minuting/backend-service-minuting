package net.huray.backend.minuting.service

import net.huray.backend.minuting.dto.Res
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
            Res.BaseInfoRes(
                name, memberType,
                Res.CompanyRes(company.id, company.name, company.ceo, company.telNumber, company.address),
                Res.TeamRes(team.id, team.name),
                spaceComponent.listPermissionByMember(this).map {
                    val space = it.space

                    Res.SpaceRes(space.id, space.name, space.icon, space.isPublic)
                }
            )
        }

}
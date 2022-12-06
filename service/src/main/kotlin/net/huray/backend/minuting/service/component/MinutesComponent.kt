package net.huray.backend.minuting.service.component

import net.huray.backend.minuting.entity.MemberEntity
import net.huray.backend.minuting.entity.SpaceEntity
import net.huray.backend.minuting.repository.MinutesAttendeeRepository
import net.huray.backend.minuting.repository.MinutesRepository
import org.springframework.stereotype.Component

@Component
class MinutesComponent(
    private val minutesRepository: MinutesRepository,
    private val minutesAttendeeRepository: MinutesAttendeeRepository
) {

    fun listAttendeeMemberAndSpace(member: MemberEntity, space: SpaceEntity) =
        minutesAttendeeRepository.findByMemberAndMinutes_Space(member, space)

}
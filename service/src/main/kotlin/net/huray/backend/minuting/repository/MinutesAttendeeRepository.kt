package net.huray.backend.minuting.repository

import net.huray.backend.minuting.entity.MemberEntity
import net.huray.backend.minuting.entity.MinutesAttendee
import net.huray.backend.minuting.entity.SpaceEntity
import org.springframework.data.jpa.repository.JpaRepository

interface MinutesAttendeeRepository : JpaRepository<MinutesAttendee, Long> {

    fun findByMemberAndMinutes_Space(member: MemberEntity, space: SpaceEntity): List<MinutesAttendee>

}
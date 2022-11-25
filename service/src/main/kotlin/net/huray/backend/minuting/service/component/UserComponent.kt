package net.huray.backend.minuting.service.component

import net.huray.backend.http.exception.NotFoundException
import net.huray.backend.minuting.entity.MemberEntity
import net.huray.backend.minuting.repository.MemberRepository
import net.huray.backend.minuting.support.ErrorMessages
import org.springframework.stereotype.Component
import java.util.*

@Component
class UserComponent(
    private val memberRepository: MemberRepository
) {

    fun get(uid: UUID): MemberEntity = memberRepository.findById(uid)
        .orElseThrow { throw NotFoundException(ErrorMessages.MEMBER_NOT_FOUND, uid) }

}
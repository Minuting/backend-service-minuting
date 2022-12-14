package net.huray.backend.minuting.service.component

import net.huray.backend.http.exception.BaseException
import net.huray.backend.http.exception.code.ErrorCode.MEMBER_NOT_FOUND
import net.huray.backend.minuting.entity.MemberEntity
import net.huray.backend.minuting.repository.MemberRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class UserComponent(
    private val memberRepository: MemberRepository
) {

    fun get(uid: UUID): MemberEntity =
        memberRepository.findById(uid).orElseThrow { throw BaseException(MEMBER_NOT_FOUND, uid) }

    fun searchByName(name: String): List<MemberEntity> =
        memberRepository.findByNameContains(name)

}
package net.huray.backend.minuting.support

import net.huray.backend.http.exception.BaseException
import net.huray.backend.http.exception.code.ErrorCode.INVALID_TOKEN
import org.springframework.stereotype.Component
import org.springframework.web.context.annotation.RequestScope
import java.util.*

@Component
@RequestScope
class AuthenticationFacade {

    var id: Long = -1L
        get() = if (flag) field else throw BaseException(INVALID_TOKEN)
    var uid: UUID = UUID.randomUUID()
        get() = if (flag) field else throw BaseException(INVALID_TOKEN)
    var email: String = ""
        get() = if (flag) field else throw BaseException(INVALID_TOKEN)
    var name: String = ""
        get() = if (flag) field else throw BaseException(INVALID_TOKEN)

    private var flag: Boolean = false

    fun setInfo(id: Long, uid: UUID, email: String, name: String) {
        this.id = id
        this.uid = uid
        this.email = email
        this.name = name
        this.flag = true
    }

}
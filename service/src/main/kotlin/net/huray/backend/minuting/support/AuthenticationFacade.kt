package net.huray.backend.minuting.support

import org.springframework.stereotype.Component
import org.springframework.web.context.annotation.RequestScope

@Component
@RequestScope
class AuthenticationFacade {

    var id: Long? = null
    var email: String? = null
    var name: String? = null

    fun setInfo(id: Long, email: String, name: String) {
        this.id = id
        this.email = email
        this.name = name
    }

}
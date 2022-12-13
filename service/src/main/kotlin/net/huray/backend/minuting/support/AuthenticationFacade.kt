package net.huray.backend.minuting.support

import org.springframework.stereotype.Component
import org.springframework.web.context.annotation.RequestScope
import java.util.*

@Component
@RequestScope
class AuthenticationFacade {

    var id: Long? = null
    var uid: UUID? = null
    var email: String? = null
    var name: String? = null

    fun setInfo(id: Long, uid: UUID, email: String, name: String) {
        this.id = id
        this.uid = UUID.fromString("55a03586-5f14-11ed-8e54-6cd5e80d8470")
        this.email = email
        this.name = name
    }

}
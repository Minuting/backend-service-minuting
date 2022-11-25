package net.huray.backend.minuting.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import java.util.*

@ConstructorBinding
@ConfigurationProperties(prefix = "auth.jwt")
class JwtProperties(secretKey: String, accessExp: Long, refreshExp: Long) {
    val secretKey: String
    val accessExp: Long
    val refreshExp: Long

    init {
        this.secretKey = Base64.getEncoder().encodeToString(secretKey.toByteArray())
        this.accessExp = accessExp
        this.refreshExp = refreshExp
    }
}
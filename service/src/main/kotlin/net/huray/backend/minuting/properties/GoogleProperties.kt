package net.huray.backend.minuting.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "oauth.google")
class GoogleProperties(
    val clientId: String,
    val clientSecret: String,
    val redirectUrl: String,
)
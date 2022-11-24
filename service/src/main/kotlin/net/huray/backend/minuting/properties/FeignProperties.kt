package net.huray.backend.minuting.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "feign.retry")
class FeignProperties(
    val period: Long,
    val maxPeriod: Long,
    val maxAttempts: Int
)
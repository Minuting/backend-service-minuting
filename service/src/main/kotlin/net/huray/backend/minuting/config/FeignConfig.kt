package net.huray.backend.minuting.config

import feign.Retryer
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@EnableFeignClients(basePackages = ["net.huray.backend.minuting"])
@Configuration
class FeignConfig(
    @Value("\${feign.retry.period}") val period: Long,
    @Value("\${feign.retry.max-period}") val maxPeriod: Long,
    @Value("\${feign.retry.max-attempts}") val maxAttempts: Int
) {
    @Bean
    fun retryer(): Retryer? {
        return Retryer.Default(period, maxPeriod, maxAttempts)
    }
}
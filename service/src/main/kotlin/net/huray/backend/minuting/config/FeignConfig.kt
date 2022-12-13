package net.huray.backend.minuting.config

import feign.Retryer
import net.huray.backend.minuting.properties.FeignProperties
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@EnableFeignClients(basePackages = ["net.huray.backend.minuting"])
@Configuration
class FeignConfig(
    private val feignProperties: FeignProperties
) {

    @Bean
    fun retryer(): Retryer = with(feignProperties) {
        Retryer.Default(period, maxPeriod, maxAttempts)
    }
    
}
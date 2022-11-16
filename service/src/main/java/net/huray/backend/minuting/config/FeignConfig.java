package net.huray.backend.minuting.config;

import feign.Retryer;
import feign.Retryer.Default;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableFeignClients(basePackages = "net.huray.backend.minuting")
@Configuration
public class FeignConfig {

    @Value("${feign.retry.period}")
    private long period;

    @Value("${feign.retry.max-period}")
    private long maxPeriod;

    @Value("${feign.retry.max-attempts}")
    private int maxAttempts;

    @Bean
    public Retryer retryer() {
        return new Default(period, maxPeriod, maxAttempts);
    }

}

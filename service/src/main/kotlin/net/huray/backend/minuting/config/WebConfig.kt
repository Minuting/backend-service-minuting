package net.huray.backend.minuting.config

import net.huray.backend.minuting.contract.Endpoint
import net.huray.backend.minuting.repository.AccountRepository
import net.huray.backend.minuting.support.AuthenticationFacade
import net.huray.backend.minuting.support.JwtInterceptor
import net.huray.backend.minuting.support.JwtProvider
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig(
    private val authenticationFacade: AuthenticationFacade,
    private val accountRepository: AccountRepository,
    private val jwtProvider: JwtProvider
): WebMvcConfigurer {

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(
            JwtInterceptor(authenticationFacade, accountRepository, jwtProvider)
        ).addPathPatterns("/**")
            .excludePathPatterns(
                "${Endpoint.AUTH}/**", "/error"
            )
    }

}
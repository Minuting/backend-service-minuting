package net.huray.backend.minuting.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiKey
import springfox.documentation.service.AuthorizationScope
import springfox.documentation.service.SecurityReference
import springfox.documentation.service.SecurityScheme
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spi.service.contexts.SecurityContext
import springfox.documentation.spring.web.plugins.Docket
import java.util.*

@Configuration
@EnableWebMvc
class SwaggerConfig {

    @Bean
    fun api(): Docket = Docket(DocumentationType.OAS_30)
        .useDefaultResponseMessages(false)
        .select()
        .apis(RequestHandlerSelectors.basePackage("net.huray.backend"))
        .paths(PathSelectors.any())
        .build()
        .apiInfo(apiInfo())
        .securityContexts(Collections.singletonList(securityContext()))
        .securitySchemes(apiKey())

    private fun apiInfo() = ApiInfoBuilder()
        .title("Minuting")
        .description("Minuting API - Swagger")
        .version("0.0.1-SNAPSHOT")
        .build()

    private fun defaultAuth(): List<SecurityReference?>? {
        val authorizationScope = AuthorizationScope("global", "accessEverything")
        val authorizationScopes: Array<AuthorizationScope> =
            arrayOf<AuthorizationScope>(authorizationScope)
        return Collections.singletonList(SecurityReference(HttpHeaders.AUTHORIZATION, authorizationScopes))
    }

    private fun securityContext(): SecurityContext? {
        return SecurityContext.builder().securityReferences(defaultAuth())
            .forPaths(PathSelectors.regex("/.*")).build()
    }

    private fun apiKey(): List<SecurityScheme>? {
        val apiKeys: MutableList<SecurityScheme> = ArrayList<SecurityScheme>()
        apiKeys.add(ApiKey("Authorization", HttpHeaders.AUTHORIZATION, "header"))
        return apiKeys
    }

}
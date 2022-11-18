package net.huray.backend.minuting.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket

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

    private fun apiInfo() = ApiInfoBuilder()
        .title("Minuting")
        .description("Minuting API - Swagger")
        .version("0.0.1-SNAPSHOT")
        .build()

}
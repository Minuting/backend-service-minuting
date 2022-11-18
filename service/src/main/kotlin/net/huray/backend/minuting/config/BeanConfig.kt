package net.huray.backend.minuting.config

import com.querydsl.jpa.impl.JPAQueryFactory
import org.modelmapper.ModelMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Configuration
class BeanConfig(
    @PersistenceContext var em: EntityManager
) {

    @Bean
    fun jpaQueryFactory(): JPAQueryFactory = JPAQueryFactory(em)

    @Bean
    fun modelMapper(): ModelMapper = ModelMapper()

}
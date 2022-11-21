package net.huray.backend.minuting

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class MinutingApplication

fun main(args: Array<String>) {
    runApplication<MinutingApplication>(*args)
}
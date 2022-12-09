package net.huray.backend.minuting

import io.kotest.core.spec.style.FunSpec
import io.kotest.extensions.spring.SpringExtension
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class MinutingApplicationTest: FunSpec({
    test("context load") {}
}) {
    override fun extensions() = listOf(SpringExtension)
}
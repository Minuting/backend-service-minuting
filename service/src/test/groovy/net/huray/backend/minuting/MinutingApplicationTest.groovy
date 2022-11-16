package net.huray.backend.minuting

import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification


@SpringBootTest
class MinutingApplicationTest extends Specification {

    def "contextLoads" () {
        expect:
        true
    }

}
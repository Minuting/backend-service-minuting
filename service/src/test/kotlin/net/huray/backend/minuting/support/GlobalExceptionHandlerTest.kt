package net.huray.backend.minuting.support

import feign.Request
import feign.Request.HttpMethod.POST
import feign.RetryableException
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.mockk
import net.huray.backend.http.exception.BaseException
import org.springframework.http.HttpStatus.*

class GlobalExceptionHandlerTest: BehaviorSpec({

    val handler = GlobalExceptionHandler()

    Given("Occur Exception") {
        val exception = Exception()

        When("Handle Exception") {
            val http = handler.handleException(exception)

            Then("Return Internal Server Error") {
                http.statusCode.shouldBe(INTERNAL_SERVER_ERROR)
                http.body!!.error.shouldBe("Internal Server Error")
            }
        }
    }

    Given("Occur RetryableException") {
        val exception = RetryableException(500, "External Server Error", POST, null, mockk<Request>())

        When("Handle RetryableException") {
            val http = handler.handleRetryableException(exception)

            Then("Return Service Unavailable") {
                http.statusCode.shouldBe(SERVICE_UNAVAILABLE)
                http.body!!.error.shouldBe("External Server Error")
            }
        }
    }

    Given("Occur BaseException") {
        val exception = BaseException(400, "code", "default", null)

        When("Handle BaseException") {
            val http = handler.handleBaseException(exception)

            Then("Return status") {
                http.statusCode.shouldBe(BAD_REQUEST)
            }
        }
    }

})
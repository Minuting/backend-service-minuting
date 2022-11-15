package net.huray.backend.minuting.error

import feign.Request
import feign.RetryableException
import org.springframework.http.ResponseEntity
import spock.lang.Specification

import static feign.Request.HttpMethod.POST
import static net.huray.backend.minuting.error.ErrorCode.External_Server_Error
import static net.huray.backend.minuting.error.ErrorCode.Internal_Server_Error
import static org.springframework.http.HttpStatus.BAD_REQUEST

class GlobalExceptionHandlerTest extends Specification {

    final GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler()

    def "handleException" () {
        given :
        final Exception exception = new Exception()

        when:
        final ResponseEntity<ErrorResponse> response = globalExceptionHandler.handleException(exception)

        then:
        response.statusCode == Internal_Server_Error.status
        response.body.message == Internal_Server_Error.message
    }

    def "handleRetryableException" () {
        given:
        final Request request = new Request(httpMethod, "/test", Collections.emptyMap(), null, null)
        final RetryableException exception = new RetryableException(status, message, httpMethod, null, request)

        when:
        final ResponseEntity<ErrorResponse> response = globalExceptionHandler.handleRetryableException(exception)

        then:
        response.statusCode == External_Server_Error.status
        response.body.message == External_Server_Error.message

        where:
        status | message | httpMethod
        500 | "Internal Server Error" | POST
        501 | "Not Implemented" | POST
    }

    def "handleGlobalException" () {
        when:
        final ResponseEntity<ErrorResponse> response = globalExceptionHandler.handleGlobalException(exception)

        then:
        response.statusCode == status
        response.body.message == message

        where:
        exception || status | message
        new GlobalException(400, "Bad Request") || BAD_REQUEST | "Bad Request"
        new GlobalException(Internal_Server_Error) || Internal_Server_Error.status | Internal_Server_Error.message
        new GlobalException(External_Server_Error, "Not Exists Function") || External_Server_Error.status | "Not Exists Function"
    }

}
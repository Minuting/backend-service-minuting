package net.huray.backend.minuting.support

import feign.RetryableException
import net.huray.backend.http.exception.BaseException
import net.huray.backend.http.res.BaseError
import net.huray.backend.http.res.BaseResult
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR
import org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ResponseEntity<BaseResult> {
        e.printStackTrace()
        return ResponseEntity(BaseResult(BaseError(500, "Internal Server Error")), INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(RetryableException::class)
    fun handleRetryableException(e: RetryableException): ResponseEntity<BaseResult> =
        ResponseEntity(BaseResult(BaseError(e.status(), "External Server Error")), SERVICE_UNAVAILABLE)

    @ExceptionHandler(BaseException::class)
    fun handleBaseException(e: BaseException): ResponseEntity<BaseResult> =
        ResponseEntity(BaseResult(BaseError(e.code, e.reason)), HttpStatus.valueOf(e.code))

}
package net.huray.backend.minuting.support

import feign.Response
import feign.RetryableException
import feign.codec.ErrorDecoder
import net.huray.backend.http.exception.BaseException
import org.springframework.stereotype.Component

@Component
class FeignErrorDecoder: ErrorDecoder {
    override fun decode(methodKey: String, response: Response): Exception =
        if (response.status() >= 500)
            RetryableException(response.status(), response.reason(), response.request().httpMethod(), null, response.request())
        else BaseException(response.status(), response.reason())
}
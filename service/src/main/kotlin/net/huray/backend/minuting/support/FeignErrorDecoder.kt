package net.huray.backend.minuting.support

import feign.Response
import feign.RetryableException
import feign.codec.ErrorDecoder
import net.huray.backend.http.exception.BaseException
import org.springframework.stereotype.Component

@Component
class FeignErrorDecoder : ErrorDecoder {

    override fun decode(methodKey: String, response: Response): Exception = with(response) {
        if (status() >= 500) RetryableException(status(), reason(), request().httpMethod(), null, request())
        else BaseException(status(), reason())
    }

}
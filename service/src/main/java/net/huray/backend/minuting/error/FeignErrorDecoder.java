package net.huray.backend.minuting.error;

import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;
import org.springframework.stereotype.Component;

@Component
public class FeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(final String methodKey, Response response) {
        return new RetryableException(response.status(), response.reason(), response.request().httpMethod(), null, response.request());
    }

}

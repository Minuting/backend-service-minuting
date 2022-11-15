package net.huray.backend.minuting.error;

import feign.RetryableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static net.huray.backend.minuting.error.ErrorCode.External_Server_Error;
import static net.huray.backend.minuting.error.ErrorCode.Internal_Server_Error;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(final Exception e) {
        e.printStackTrace();
        return getResponse(Internal_Server_Error);
    }

    @ExceptionHandler(RetryableException.class)
    public ResponseEntity<ErrorResponse> handleRetryableException(final RetryableException e) {
        return getResponse(External_Server_Error);
    }

    @ExceptionHandler(GlobalException.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(final GlobalException e) {
        return getResponse(e.getStatus(), e.getMessage());
    }

    private ResponseEntity<ErrorResponse> getResponse(final ErrorCode code) {
        final ErrorResponse response = new ErrorResponse(code.getMessage());
        return new ResponseEntity<>(response, code.getStatus());
    }

    private ResponseEntity<ErrorResponse> getResponse(final HttpStatus code, final String message) {
        final ErrorResponse response = new ErrorResponse(message);
        return new ResponseEntity<>(response, code);
    }

}

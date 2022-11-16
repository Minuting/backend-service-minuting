package net.huray.backend.minuting.error;

import org.springframework.http.HttpStatus;

public class GlobalException extends RuntimeException {

    private final HttpStatus status;
    private final String message;

    public GlobalException(final int status, final String message) {
        this.status = HttpStatus.valueOf(status);
        this.message = message;
    }

    public GlobalException(final ErrorCode errorCode) {
        this.status = errorCode.getStatus();
        this.message = errorCode.getMessage();
    }

    public GlobalException(final ErrorCode errorCode, final String message) {
        this.status = errorCode.getStatus();
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

}

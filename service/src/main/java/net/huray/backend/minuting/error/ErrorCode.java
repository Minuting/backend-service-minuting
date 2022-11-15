package net.huray.backend.minuting.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    Internal_Server_Error(500, "Internal Server Error"),
    External_Server_Error(503, "External Server Error");

    private final HttpStatus status;
    private final String message;

    ErrorCode(final int status, final String message) {
        this.status = HttpStatus.valueOf(status);
        this.message = message;
    }

}
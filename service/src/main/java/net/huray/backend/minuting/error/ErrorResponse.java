package net.huray.backend.minuting.error;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ErrorResponse {

    private final LocalDateTime timestamp;
    private final String message;

    public ErrorResponse(final String message) {
        this.timestamp = LocalDateTime.now();
        this.message = message;
    }

}

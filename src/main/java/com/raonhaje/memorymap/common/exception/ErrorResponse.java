package com.raonhaje.memorymap.common.exception;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Getter
public class ErrorResponse {
    private final String message;
    private final int status;
    private final String path;
    private final Instant timestamp;

    @Builder
    public ErrorResponse(String message, int status, String path, Instant timestamp) {
        this.message = message;
        this.status = status;
        this.path = path;
        this.timestamp = timestamp;
    }

    public static ErrorResponse of(ErrorCode code, String path) {
        return ErrorResponse.builder()
                .message(code.getMessage())
                .status(code.getStatus())
                .path(path)
                .timestamp(Instant.now())
                .build();
    }
}

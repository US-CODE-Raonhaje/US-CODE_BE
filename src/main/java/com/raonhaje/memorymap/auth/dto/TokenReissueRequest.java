package com.raonhaje.memorymap.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record TokenReissueRequest (

        @Schema(description = "우리 서비스에서 발급한 refresh token", example = "eyJhbGciOi...")
        String refreshToken
) {
}

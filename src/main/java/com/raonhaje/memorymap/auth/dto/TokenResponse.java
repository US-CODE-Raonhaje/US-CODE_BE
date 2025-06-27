package com.raonhaje.memorymap.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record TokenResponse(

        @Schema(description = "액세스 토큰", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
        String accessToken,
        @Schema(description = "리프레시 토큰", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
        String refreshToken,
        @Schema(description = "추가 정보 입력 필요 여부", example = "true")
        boolean isAdditionalInfoRequired
) {
}

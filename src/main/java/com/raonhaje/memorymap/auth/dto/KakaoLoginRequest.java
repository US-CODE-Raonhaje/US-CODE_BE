package com.raonhaje.memorymap.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record KakaoLoginRequest(

        @Schema(description = "카카오에서 발급받은 인가 코드", example = "1234567890abcdef")
        String code
) {
}

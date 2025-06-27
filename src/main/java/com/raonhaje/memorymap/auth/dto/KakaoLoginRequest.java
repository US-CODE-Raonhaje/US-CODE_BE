package com.raonhaje.memorymap.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record KakaoLoginRequest(

        @Schema(description = "카카오에서 발급받은 access token", example = "kakao_access_token_here")
        String accessToken
) {
}

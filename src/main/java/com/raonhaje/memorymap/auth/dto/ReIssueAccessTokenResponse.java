package com.raonhaje.memorymap.auth.dto;

public record ReIssueAccessTokenResponse(

        String accessToken,
        String refreshToken
) {
}

package com.raonhaje.memorymap.auth.dto;

public record RefreshRequest (

        String refreshToken
) {

    public String getRefreshToken() {
        return refreshToken;
    }
}

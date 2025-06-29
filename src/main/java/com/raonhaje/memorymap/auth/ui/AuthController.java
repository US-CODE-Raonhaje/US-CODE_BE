package com.raonhaje.memorymap.auth.ui;

import com.raonhaje.memorymap.auth.application.RefreshTokenService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController implements AuthApiDocs {

    private final RefreshTokenService refreshTokenService;

    @Override
    public ResponseEntity<?> refreshAccessToken(@RequestHeader(name = "Refresh-Token") String refreshToken, HttpServletResponse response) {
        String accessToken = refreshTokenService.refreshAccessToken(refreshToken);
        response.addHeader("Authorization", "Bearer " + accessToken);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    public ResponseEntity<?> logout(@RequestHeader(name = "Refresh-Token") String refreshToken) {
        refreshTokenService.deleteRefreshToken(refreshToken);

        return ResponseEntity.status(HttpStatus.OK).body("로그아웃 성공");
    }
}

package com.raonhaje.memorymap.auth.ui;

import com.raonhaje.memorymap.auth.dto.RefreshRequest;
import com.raonhaje.memorymap.auth.dto.TokenResponse;
import com.raonhaje.memorymap.common.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController implements AuthApiDocs {

    private final JwtProvider jwtTokenProvider;

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshAccessToken(@RequestBody RefreshRequest request) {
        if (!jwtTokenProvider.validateToken(request.getRefreshToken())) {
            return ResponseEntity.badRequest().body("Invalid refresh token");
        }

        String email = jwtTokenProvider.getEmailFromToken(request.getRefreshToken());
        String newAccessToken = jwtTokenProvider.createAccessToken(email);

        return ResponseEntity.ok(new TokenResponse(newAccessToken));
    }
}

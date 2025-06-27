package com.raonhaje.memorymap.auth.ui;

import com.raonhaje.memorymap.auth.dto.TokenReissueRequest;
import com.raonhaje.memorymap.auth.dto.TokenResponse;
import com.raonhaje.memorymap.member.dto.MemberRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController implements AuthApiDocs {

    @Override
    public ResponseEntity<TokenResponse> kakaoLogin(String code) {
        return ResponseEntity.ok(new TokenResponse("mock accessToken", "mock refreshToken", true));
    }

    @Override
    public ResponseEntity<Long> signUp(MemberRequest request) {
        return ResponseEntity.ok(1L); // Mock response for sign-up
    }

    @Override
    public ResponseEntity<TokenResponse> reissueAccessToken(TokenReissueRequest tokenRequest) {
        return ResponseEntity.ok(new TokenResponse("mock newAccessToken", "mock newRefreshToken", true));
    }

    @Override
    public ResponseEntity<Void> logout() {
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}

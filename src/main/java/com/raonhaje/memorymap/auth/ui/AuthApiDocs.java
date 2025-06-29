package com.raonhaje.memorymap.auth.ui;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@Tag(name = "회원 인증", description = "회원 인증 관련 API 문서입니다.")
public interface AuthApiDocs {

    @Operation(summary = "Access Token 갱신", description = "Refresh Token을 사용하여 Access Token을 갱신합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Access Token 갱신 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "401", description = "인증 실패")
    })
    @PostMapping("/refresh")
    ResponseEntity<?> refreshAccessToken(@RequestHeader(name = "Refresh-Token") String refreshToken, HttpServletResponse response);

    @Operation(summary = "로그아웃 및 Refresh Token 삭제", description = "로그아웃 시, Refresh Token을 삭제합니다.", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "로그아웃 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "401", description = "인증 실패")
    })
    @PostMapping("/logout")
    ResponseEntity<?> logout(@RequestHeader(name = "Authorization") String authorizationHeader);
}

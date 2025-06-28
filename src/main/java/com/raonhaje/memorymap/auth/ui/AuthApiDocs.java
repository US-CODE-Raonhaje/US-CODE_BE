package com.raonhaje.memorymap.auth.ui;

import com.raonhaje.memorymap.auth.dto.RefreshRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "회원 인증", description = "회원 인증 관련 API 문서입니다.")
public interface AuthApiDocs {

    @Operation(summary = "Access Token 갱신", description = "Refresh Token을 사용하여 Access Token을 갱신합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Access Token 갱신 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "401", description = "인증 실패")
    })
    @PostMapping("/refresh")
    ResponseEntity<?> refreshAccessToken(@RequestBody RefreshRequest request);
}

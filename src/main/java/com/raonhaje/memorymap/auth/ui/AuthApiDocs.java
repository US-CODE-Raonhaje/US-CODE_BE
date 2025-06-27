package com.raonhaje.memorymap.auth.ui;

import com.raonhaje.memorymap.auth.dto.KakaoLoginRequest;
import com.raonhaje.memorymap.auth.dto.TokenReissueRequest;
import com.raonhaje.memorymap.auth.dto.TokenResponse;
import com.raonhaje.memorymap.member.dto.MemberRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "회원 인증", description = "회원 인증 관련 API 문서입니다.")
public interface AuthApiDocs {

    @Operation(summary = "카카오 로그인", description = "카카오 로그인을 통해 회원 인증을 수행합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공",
                    content = @Content(schema = @Schema(implementation = TokenResponse.class))),
            @ApiResponse(responseCode = "401", description = "인증 실패")
    })
    @PostMapping("/kakao")
    ResponseEntity<TokenResponse> kakaoLogin(@RequestBody KakaoLoginRequest request);

    @Operation(summary = "회원 추가 정보 입력 후 회원가입", description = "회원의 추가 정보를 입력한 후, 회원가입합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공",
                    content = @Content(schema = @Schema(implementation = Long.class)))
    })
    @PostMapping("/signup")
    ResponseEntity<Long> signUp(@RequestBody MemberRequest request);

    @Operation(summary = "액세스 토큰 재발급", description = "액세스 토큰을 재발급합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공",
                    content = @Content(schema = @Schema(implementation = TokenResponse.class))),
            @ApiResponse(responseCode = "401", description = "인증 실패")
    })
    @PostMapping("/reissue")
    ResponseEntity<TokenResponse> reissueAccessToken(@RequestBody TokenReissueRequest tokenRequest);

    @Operation(summary = "로그아웃 및 리프레시 토큰 삭제", description = "로그아웃을 수행하고 리프레시 토큰을 삭제합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "401", description = "인증 실패")
    })
    @PostMapping("/logout")
    ResponseEntity<Void> logout();
}

package com.raonhaje.memorymap.member.ui;

import com.raonhaje.memorymap.member.dto.MemberInfoResponse;
import com.raonhaje.memorymap.member.dto.MemberSignUpRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@Tag(name = "회원 정보", description = "회원 정보 관련 API 문서입니다.")
public interface MemberApiDocs {

    @Operation(summary = "내 정보 조회", description = "JWT 토큰을 기반으로 자신의 정보를 조회합니다.", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공",
                    content = @Content(schema = @Schema(implementation = MemberInfoResponse.class))),
            @ApiResponse(responseCode = "401", description = "인증 실패")
    })
    @GetMapping("/me")
    ResponseEntity<MemberInfoResponse> getMyInfo(@RequestHeader(name = "Authorization") String authorizationHeader);

    @Operation(summary = "회원 정보 등록", description = "구글 회원가입 후, 회원 정보를 등록합니다.", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "회원 정보 등록 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "401", description = "인증 실패")
    })
    @PostMapping("/signup")
    ResponseEntity<Long> signUp(@RequestHeader(name = "Authorization") String authorizationHeader, @RequestBody MemberSignUpRequest request);
}

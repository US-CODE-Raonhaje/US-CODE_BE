package com.raonhaje.memorymap.member.ui;

import com.raonhaje.memorymap.member.dto.MemberInfoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
}

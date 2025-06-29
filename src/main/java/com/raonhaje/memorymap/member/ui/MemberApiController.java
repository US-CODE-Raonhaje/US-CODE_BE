package com.raonhaje.memorymap.member.ui;

import com.raonhaje.memorymap.common.jwt.JwtProvider;
import com.raonhaje.memorymap.member.application.MemberService;
import com.raonhaje.memorymap.member.dto.MemberInfoResponse;
import com.raonhaje.memorymap.member.dto.MemberSignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class MemberApiController implements MemberApiDocs {

    private final MemberService memberService;
    private final JwtProvider jwtProvider;

    @Override
    public ResponseEntity<MemberInfoResponse> getMyInfo(@RequestHeader(name = "Authorization") String authorizationHeader) {
        String email = jwtProvider.getEmailFromToken(authorizationHeader);
        MemberInfoResponse memberInfo = memberService.getMemberInfo(email);

        return ResponseEntity.ok(memberInfo);
    }

    @Override
    public ResponseEntity<Long> signUp(@RequestHeader(name = "Authorization") String authorizationHeader, @RequestBody MemberSignUpRequest request) {
        String email = jwtProvider.getEmailFromToken(authorizationHeader);
        Long memberId = memberService.signUp(email, request).getMemberId();

        return ResponseEntity.status(HttpStatus.CREATED).body(memberId);
    }
}

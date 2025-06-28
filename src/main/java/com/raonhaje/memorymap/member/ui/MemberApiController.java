package com.raonhaje.memorymap.member.ui;

import com.raonhaje.memorymap.common.jwt.JwtProvider;
import com.raonhaje.memorymap.member.application.MemberService;
import com.raonhaje.memorymap.member.domain.Member;
import com.raonhaje.memorymap.member.dto.MemberInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
        Member member = memberService.findByKakaoId(jwtProvider.extractKakaoId(authorizationHeader));

        MemberInfoResponse memberInfoResponse = MemberInfoResponse.from(member);

        return ResponseEntity.ok(memberInfoResponse);
    }
}

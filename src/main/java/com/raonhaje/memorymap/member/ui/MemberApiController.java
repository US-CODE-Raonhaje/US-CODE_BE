package com.raonhaje.memorymap.member.ui;

import com.raonhaje.memorymap.common.exception.BusinessException;
import com.raonhaje.memorymap.common.exception.ErrorCode;
import com.raonhaje.memorymap.common.jwt.JwtProvider;
import com.raonhaje.memorymap.member.application.MemberService;
import com.raonhaje.memorymap.member.dto.MemberInfoResponse;
import com.raonhaje.memorymap.member.dto.MemberSignUpRequest;
import com.raonhaje.memorymap.member.repository.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class MemberApiController implements MemberApiDocs {

    private final MemberService memberService;
    private final MemberJpaRepository memberJpaRepository;
    private final JwtProvider jwtProvider;

    @Override
    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Long> signUp(@RequestBody MemberSignUpRequest request) {
        Long memberId = memberService.signUp(request).getMemberId();
        return ResponseEntity.status(HttpStatus.CREATED).body(memberId);
    }

    @Override
    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<MemberInfoResponse> getMyInfo(@RequestHeader(name = "Authorization") String authorizationHeader) {
        String email = jwtProvider.getEmailFromToken(authorizationHeader);

        MemberInfoResponse memberInfo = memberJpaRepository.findByEmail(email)
                .map(MemberInfoResponse::from)
                .orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_FOUND));

        return ResponseEntity.ok(memberInfo);
    }
}

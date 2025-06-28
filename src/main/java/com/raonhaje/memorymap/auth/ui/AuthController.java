package com.raonhaje.memorymap.auth.ui;

import com.raonhaje.memorymap.auth.application.KakaoAuthService;
import com.raonhaje.memorymap.auth.application.RefreshTokenService;
import com.raonhaje.memorymap.auth.domain.RefreshToken;
import com.raonhaje.memorymap.auth.dto.KakaoUserInfo;
import com.raonhaje.memorymap.auth.dto.ReIssueAccessTokenResponse;
import com.raonhaje.memorymap.auth.dto.TokenResponse;
import com.raonhaje.memorymap.common.jwt.JwtProvider;
import com.raonhaje.memorymap.member.application.MemberService;
import com.raonhaje.memorymap.member.domain.Member;
import com.raonhaje.memorymap.member.dto.MemberRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController implements AuthApiDocs {

    private final KakaoAuthService kakaoAuthService;
    private final MemberService memberService;
    private final JwtProvider jwtProvider;
    private final RefreshTokenService refreshTokenService;

    @Override
    public ResponseEntity<TokenResponse> kakaoLogin(@RequestParam("code") String code) {
        String kakaoAccessToken = kakaoAuthService.getAccessToken(code);

        KakaoUserInfo kakaoUserInfo = kakaoAuthService.getUserInfo(kakaoAccessToken);

        Member member = memberService.findOrCreateMember(kakaoUserInfo);

        String accessToken = jwtProvider.createAccessToken(member.getMemberId());
        String refreshToken = jwtProvider.createRefreshToken(member.getMemberId());

        refreshTokenService.save(new RefreshToken(refreshToken, member.getMemberId()));

        boolean isAdditionalInfoRequired = member.isAdditionalInfoRequired();

        return ResponseEntity.ok(new TokenResponse(accessToken, refreshToken, isAdditionalInfoRequired));
    }

    @Override
    public ResponseEntity<Long> signUp(@RequestHeader("Authorization") String authorizationHeader, MemberRequest request) {
        String token = authorizationHeader.replace("Bearer ", "");
        Long kakaoId = jwtProvider.extractKakaoId(token);

        Member member = memberService.signUp(kakaoId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(member.getMemberId());
    }

    @Override
    public ResponseEntity<ReIssueAccessTokenResponse> reissueAccessToken(@RequestHeader("refresh") String refreshToken) {
        Long kakaoId;

        try {
            kakaoId = jwtProvider.extractKakaoId(refreshToken);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String newAccessToken = jwtProvider.createAccessToken(kakaoId);
        String newRefreshToken = jwtProvider.createRefreshToken(kakaoId);

        RefreshToken existingRefreshToken = refreshTokenService.findById(kakaoId);

        existingRefreshToken.updateRefreshToken(newRefreshToken);

        return ResponseEntity.status(HttpStatus.OK).body(new ReIssueAccessTokenResponse(newAccessToken, newRefreshToken));
    }

    @Override
    public ResponseEntity<Void> logout(@RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "");
        Long kakaoId = jwtProvider.extractKakaoId(token);

        Member member = memberService.findByKakaoId(kakaoId);

        refreshTokenService.deleteRefreshToken(member.getMemberId());

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}

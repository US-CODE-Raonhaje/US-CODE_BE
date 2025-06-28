package com.raonhaje.memorymap.member.application;

import com.raonhaje.memorymap.auth.dto.KakaoUserInfo;
import com.raonhaje.memorymap.auth.repository.RefreshTokenRepository;
import com.raonhaje.memorymap.common.exception.BusinessException;
import com.raonhaje.memorymap.common.exception.ErrorCode;
import com.raonhaje.memorymap.member.domain.Member;
import com.raonhaje.memorymap.member.dto.MemberRequest;
import com.raonhaje.memorymap.member.repository.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberJpaRepository memberJpaRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    public Member findOrCreateMember(KakaoUserInfo kakaoUserInfo) {
        return memberJpaRepository.findByKakaoId(kakaoUserInfo.id())
                .orElseGet(() -> {
                    Member newMember = Member.createFromKakao(kakaoUserInfo);
                    return memberJpaRepository.save(newMember);
                });
    }

    @Transactional
    public Member signUp(Long kakaoId, MemberRequest request) {
        if (memberJpaRepository.findByKakaoId(kakaoId).isPresent()) {
            throw new BusinessException(ErrorCode.EXISTING_MEMBER);
        }

        Member member = Member.create(kakaoId, request.nickname(), request.age());

        return memberJpaRepository.save(member);
    }

    public Member findByKakaoId(Long kakaoId) {
        return memberJpaRepository.findByKakaoId(kakaoId)
                .orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_FOUND));
    }
}

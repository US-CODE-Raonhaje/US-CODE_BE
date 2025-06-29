package com.raonhaje.memorymap.member.application;

import com.raonhaje.memorymap.common.exception.BusinessException;
import com.raonhaje.memorymap.common.exception.ErrorCode;
import com.raonhaje.memorymap.member.domain.Location;
import com.raonhaje.memorymap.member.domain.Member;
import com.raonhaje.memorymap.member.dto.MemberInfoResponse;
import com.raonhaje.memorymap.member.dto.MemberSignUpRequest;
import com.raonhaje.memorymap.member.repository.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberJpaRepository memberJpaRepository;

    public MemberInfoResponse getMemberInfo(String email) {
        return memberJpaRepository.findByEmail(email)
                .map(MemberInfoResponse::from)
                .orElseThrow(() -> new IllegalArgumentException("Member not found with email: " + email));
    }

    @Transactional
    public Member signUp(String email, MemberSignUpRequest request) {
        Member member = memberJpaRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_FOUND));

        member.setAdditionalInfo(request.nickname(),
                request.age(),
                request.profileImageUrl(),
                Location.create(request.latitude(), request.longitude(), request.address()));

        return memberJpaRepository.save(member);
    }
}

package com.raonhaje.memorymap.member.application;

import com.raonhaje.memorymap.common.exception.BusinessException;
import com.raonhaje.memorymap.common.exception.ErrorCode;
import com.raonhaje.memorymap.member.domain.Location;
import com.raonhaje.memorymap.member.domain.Member;
import com.raonhaje.memorymap.member.dto.MemberSignUpRequest;
import com.raonhaje.memorymap.member.repository.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberJpaRepository memberJpaRepository;

    @Transactional
    public Member signUp(MemberSignUpRequest request) {
        if (memberJpaRepository.existsByEmail(request.email())) {
            throw new BusinessException(ErrorCode.EXISTING_EMAIL);
        }

        if (memberJpaRepository.existsByNickname(request.nickname())) {
            throw new BusinessException(ErrorCode.EXISTING_NICKNAME);
        }

        Location location = Location.create(request.latitude(), request.longitude(), request.address());
        Member member = Member.create(request.email(), request.name(), request.nickname(), request.age(), request.profileImageUrl(), location);

        return memberJpaRepository.save(member);
    }
}

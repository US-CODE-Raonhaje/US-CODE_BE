package com.raonhaje.memorymap.member.application;

import com.raonhaje.memorymap.common.exception.BusinessException;
import com.raonhaje.memorymap.member.domain.Location;
import com.raonhaje.memorymap.member.domain.Member;
import com.raonhaje.memorymap.member.dto.MemberSignUpRequest;
import com.raonhaje.memorymap.member.repository.MemberJpaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Mock
    MemberJpaRepository memberJpaRepository;

    @InjectMocks
    MemberService memberService;

    MemberSignUpRequest createRequest() {
        return new MemberSignUpRequest("testEmail", "홍길동", "길동이", 25, "profileImageUrl.jpg", 37.5665, 126.9784, "서울특별시 마포구");
    }

    @Test
    @DisplayName("회원 등록 성공 테스트")
    void signUpSuccess() {
        // given
        MemberSignUpRequest request = createRequest();

        // when
        when(memberJpaRepository.existsByEmail(request.email())).thenReturn(false);
        when(memberJpaRepository.existsByNickname(request.nickname())).thenReturn(false);
        Location location = Location.create(request.latitude(), request.longitude(), request.address());
        Member member = Member.create(request.email(), request.name(), request.nickname(), request.age(), request.profileImageUrl(), location);
        when(memberJpaRepository.save(any(Member.class))).thenReturn(member);

        Member result = memberService.signUp(request);

        // then
        assertEquals(result.getEmail(), request.email());
        assertEquals(result.getName(), request.name());
        assertEquals(result.getNickname(), request.nickname());
        assertEquals(result.getAge(), request.age());
        assertEquals(result.getImageUrl(), request.profileImageUrl());
        assertEquals(result.getLocation().getLatitude(), request.latitude());
        assertEquals(result.getLocation().getLongitude(), request.longitude());
        assertEquals(result.getLocation().getAddress(), request.address());
    }
    
    @Test
    @DisplayName("회원 등록 실패 - 이메일 중복 테스트")
    void signUpFailByEmail() {
        // given
        MemberSignUpRequest request = createRequest();

        // when
        when(memberJpaRepository.existsByEmail(request.email())).thenReturn(true);
        
        // then
        assertThrows(BusinessException.class, () -> memberService.signUp(request));
    }

    @Test
    @DisplayName("회원 등록 실패 - 닉네임 중복 테스트")
    void signUpFailByNickname() {
        // given
        MemberSignUpRequest request = createRequest();
        
        // when
        when(memberJpaRepository.existsByNickname(request.nickname())).thenReturn(true);
        
        // then
        assertThrows(BusinessException.class, () -> memberService.signUp(request));
    }
}

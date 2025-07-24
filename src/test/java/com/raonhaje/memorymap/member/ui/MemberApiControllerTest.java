package com.raonhaje.memorymap.member.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.raonhaje.memorymap.common.exception.BusinessException;
import com.raonhaje.memorymap.common.exception.ErrorCode;
import com.raonhaje.memorymap.common.jwt.JwtProvider;
import com.raonhaje.memorymap.member.application.MemberService;
import com.raonhaje.memorymap.member.domain.Location;
import com.raonhaje.memorymap.member.domain.Member;
import com.raonhaje.memorymap.member.dto.MemberInfoResponse;
import com.raonhaje.memorymap.member.dto.MemberSignUpRequest;
import com.raonhaje.memorymap.member.repository.MemberJpaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(MemberApiController.class)
@AutoConfigureMockMvc(addFilters = false)
class MemberApiControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    MemberService memberService;

    @MockitoBean
    MemberJpaRepository memberJpaRepository;

    @MockitoBean
    JwtProvider jwtProvider;

    @Autowired
    ObjectMapper objectMapper;

    MemberSignUpRequest createRequest() {
        return new MemberSignUpRequest(
                "test@email.com", "홍길동", "길동이", 25, "profile.jpg", 37.5665, 126.9784, "서울특별시 마포구"
        );
    }

    @Test
    @DisplayName("회원가입 성공 시 201 Created와 회원 ID 반환")
    public void signUpSuccess() throws Exception {
        MemberSignUpRequest request = createRequest();

        Member member = Mockito.mock(Member.class);

        when(member.getMemberId()).thenReturn(1L);
        when(memberService.signUp(any(MemberSignUpRequest.class))).thenReturn(member);

        mockMvc.perform(post("/api/v1/members/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(content().string("1"));
    }

    @Test
    @DisplayName("이메일 중복 시 400 Bad Request 반환")
    void signUpFailByEmail() throws Exception {
        MemberSignUpRequest request = createRequest();
        when(memberService.signUp(any(MemberSignUpRequest.class)))
                .thenThrow(new BusinessException(ErrorCode.EXISTING_EMAIL));

        mockMvc.perform(post("/api/v1/members/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("닉네임 중복 시 400 Bad Request 반환")
    void signUpFailByNickname() throws Exception {
        MemberSignUpRequest request = createRequest();
        when(memberService.signUp(any(MemberSignUpRequest.class)))
                .thenThrow(new BusinessException(ErrorCode.EXISTING_NICKNAME));

        mockMvc.perform(post("/api/v1/members/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("회원 정보 조회 성공 시 200 OK와 회원 정보 반환")
    void getMyInfoSuccess() throws Exception {
        // given
        String token = "Bearer test.jwt.token";
        String email = "test@email.com";
        Member member = Mockito.mock(Member.class);
        Location location = Mockito.mock(Location.class);

        when(location.getAddress()).thenReturn("서울특별시 마포구");
        when(member.getLocation()).thenReturn(location);

        when(member.getNickname()).thenReturn("길동이");

        when(jwtProvider.getEmailFromToken(token)).thenReturn(email);
        when(memberJpaRepository.findByEmail(email)).thenReturn(Optional.of(member));

        // 실제 MemberInfoResponse 생성자에 맞게 값 세팅
        MemberInfoResponse response = MemberInfoResponse.from(member);

        // when & then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/members/me")
                        .header("Authorization", token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }
}

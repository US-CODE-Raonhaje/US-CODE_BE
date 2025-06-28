package com.raonhaje.memorymap.member.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MemberTest {

    @Test
    @DisplayName("Member 생성 테스트")
    void createMemberTest() {
        // given
        Long kakaoId = 1234L;
        String nickname = "testNickname";
        Integer age = 25;

        // when
        Member member = Member.create(kakaoId, nickname, age);

        // then
        assertEquals(kakaoId, member.getKakaoId());
        assertEquals("testNickname", member.getNickname());
        assertEquals(25, member.getAge());
    }

    @Test
    @DisplayName("Member 업데이트 테스트")
    void updateMemberTest() {
        // given
        Member member = Member.create(1234L, "testNickname", 25);
        String newNickname = "updatedNickname";
        String newImageUrl = "http://example.com/image.jpg";

        // when
        member.updateMember(newNickname, newImageUrl);

        // then
        assertEquals("updatedNickname", member.getNickname());
        assertEquals("http://example.com/image.jpg", member.getImageUrl());
    }
}

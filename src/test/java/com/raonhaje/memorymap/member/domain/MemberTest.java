package com.raonhaje.memorymap.member.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MemberTest {

    @Test
    @DisplayName("Member 생성 테스트")
    void createMemberTest() {
        // given
        String email = "testEmail@gmail.com";
        String name = "testname";

        // when
        Member member = Member.create(email, name);

        // then
        assertEquals(email, member.getEmail());
        assertEquals(name, member.getName());
    }

    @Test
    @DisplayName("Member 업데이트 테스트")
    void updateMemberTest() {
        // given
        Member member = Member.create("testEmail@gmail.com", "testNickname");
        String newNickname = "updatedNickname";
        String newImageUrl = "http://example.com/image.jpg";

        // when
        member.updateMember(newNickname, newImageUrl);

        // then
        assertEquals("updatedNickname", member.getNickname());
        assertEquals("http://example.com/image.jpg", member.getImageUrl());
    }
}

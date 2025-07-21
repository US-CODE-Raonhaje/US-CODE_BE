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
        String name = "testName";
        String nickname = "testNickname";
        int age = 25;
        String imageUrl = "testImageUrl";

        Location location = Location.create(37.5665, 126.9784, "서울특별시 마포구");

        // when
        Member member = Member.create(email, name, nickname, age, imageUrl, location);

        // then
        assertEquals(email, member.getEmail());
        assertEquals(name, member.getName());
        assertEquals(nickname, member.getNickname());
        assertEquals(age, member.getAge());
        assertEquals(imageUrl, member.getImageUrl());
        assertEquals(location, member.getLocation());
    }

    @Test
    @DisplayName("Member 업데이트 테스트")
    void updateMemberTest() {
        // given
        Location location = Location.create(37.5665, 126.9784, "서울특별시 마포구");
        Member member = Member.create("testEmail@gmail.com", "testName", "testNickname", 25, "testImageUrl", location);
        String newNickname = "updatedNickname";
        String newImageUrl = "http://example.com/image.jpg";

        // when
        member.updateMember(newNickname, newImageUrl);

        // then
        assertEquals("updatedNickname", member.getNickname());
        assertEquals("http://example.com/image.jpg", member.getImageUrl());
    }
}

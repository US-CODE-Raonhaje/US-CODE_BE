package com.raonhaje.memorymap.like.domain;

import com.raonhaje.memorymap.member.domain.Member;
import com.raonhaje.memorymap.post.domain.Post;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LikeTest {

    @Test
    @DisplayName("Like 생성 테스트")
    void createLikeTest() {
        // given
        Member member = Member.create("testOauthId", "testNickname", 25);
        Post post = Post.create("Test Title", "Test Content");

        // when
        Like like = Like.create(member, post);

        // then
        assertEquals(member, like.getMember());
        assertEquals(post, like.getPost());
    }
}

package com.raonhaje.memorymap.post.domain;

import com.raonhaje.memorymap.member.domain.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostTest {

    @Test
    @DisplayName("Post 생성 테스트")
    void createPostTest() {
        // given
        String title = "Test Post";
        String content = "This is a test post.";

        // when
        Post post = Post.create(title, content);

        // then
        assertEquals(title, post.getTitle());
        assertEquals(content, post.getContent());
    }

    @Test
    @DisplayName("Post 업데이트 테스트")
    void updatePostTest() {
        // given
        Post post = Post.create("Test Title", "Test Content");
        String newTitle = "Updated Title";
        String newContent = "Updated Content";

        // when
        post.updatePost(newTitle, newContent);

        // then
        assertEquals(newTitle, post.getTitle());
        assertEquals(newContent, post.getContent());
    }
}

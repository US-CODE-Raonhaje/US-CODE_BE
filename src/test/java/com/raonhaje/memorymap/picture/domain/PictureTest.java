package com.raonhaje.memorymap.picture.domain;

import com.raonhaje.memorymap.post.domain.Post;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PictureTest {

    @Test
    @DisplayName("Picture 생성 테스트")
    void createPictureTest() {
        // given
        Post post = Post.create("Test Title", "Test Content");
        String imageUrl = "http://example.com/image.jpg";

        // when
        Picture picture = Picture.create(post, imageUrl);

        // then
        assertEquals(imageUrl, picture.getImageUrl());
        assertEquals(post, picture.getPost());
    }
}
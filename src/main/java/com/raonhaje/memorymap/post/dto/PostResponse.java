package com.raonhaje.memorymap.post.dto;

import com.raonhaje.memorymap.post.domain.Post;

public record PostResponse(

        String title,
        String content,
        String address,
        String category,
        String imageUrl
) {
    public static PostResponse from(Post post) {
        return new PostResponse(
                post.getTitle(),
                post.getContent(),
                post.getAddress(),
                post.getCategory().getName(),
                post.getPicture().getImageUrl()
        );
    }
}

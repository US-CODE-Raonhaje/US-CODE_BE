package com.raonhaje.memorymap.post.dto;

import java.util.List;

public record PostListResponse(

        List<PostResponse> posts,
        int totalCount
    ) {
}

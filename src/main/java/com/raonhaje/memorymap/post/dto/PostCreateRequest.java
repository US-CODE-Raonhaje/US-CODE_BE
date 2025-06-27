package com.raonhaje.memorymap.post.dto;

import org.springframework.web.bind.annotation.RequestParam;

public record PostCreateRequest(

        String title,
        String content,
        double userLatitude,
        double userLongitude
) {
}

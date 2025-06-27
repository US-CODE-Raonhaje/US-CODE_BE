package com.raonhaje.memorymap.post.dto;

public record PostCreateRequest(

        String title,
        String content,
        double userLatitude,
        double userLongitude
) {
}

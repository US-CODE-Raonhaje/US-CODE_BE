package com.raonhaje.memorymap.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record MemberSignUpRequest(

        @Schema(description = "사용자 닉네임", example = "라온하제", required = true)
        String nickname,

        @Schema(description = "연령대", example = "25", required = true)
        int age,

        @Schema(description = "사용자 프로필 사진 URL", example = "https://example.com/profile.jpg", required = true)
        String profileImageUrl,

        @Schema(description = "현재 위치의 위도", example = "126.9784", required = true)
        double latitude,

        @Schema(description = "현재 위치의 경도", example = "37.5665", required = true)
        double longitude,

        @Schema(description = "현재 위치 주소", example = "서울특별시 마포구", required = true)
        String address
) {
}

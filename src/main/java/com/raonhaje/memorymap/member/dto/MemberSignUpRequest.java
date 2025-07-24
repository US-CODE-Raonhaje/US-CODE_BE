package com.raonhaje.memorymap.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record MemberSignUpRequest(

        @Schema(description = "사용자 이메일", example = "example1234@gmail.com")
        String email,

        @Schema(description = "사용자 이름", example = "최지한")
        String name,

        @Schema(description = "사용자 닉네임", example = "라온하제")
        String nickname,

        @Schema(description = "사용자의 나이", example = "25")
        int age,

        @Schema(description = "사용자 프로필 사진 URL", example = "https://example.com/profile.jpg")
        String profileImageUrl,

        @Schema(description = "현재 위치의 위도", example = "126.9784")
        double latitude,

        @Schema(description = "현재 위치의 경도", example = "37.5665")
        double longitude,

        @Schema(description = "현재 위치 주소", example = "서울특별시 마포구")
        String address
) {
}

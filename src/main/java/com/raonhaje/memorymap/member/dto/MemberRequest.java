package com.raonhaje.memorymap.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record MemberRequest(

        @Schema(description = "사용자 닉네임", example = "라온하제", required = true)
        String nickname,

        @Schema(description = "연령대", example = "20~29", required = true)
        String ageRange,

        @Schema(description = "현재 위치", example = "서울특별시 마포구", required = true)
        String location
) {
}

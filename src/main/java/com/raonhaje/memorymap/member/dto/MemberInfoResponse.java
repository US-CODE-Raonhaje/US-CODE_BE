package com.raonhaje.memorymap.member.dto;

import com.raonhaje.memorymap.member.domain.Member;

public record MemberInfoResponse(

        String nickname,
        Integer age,
        String address
) {
    public static MemberInfoResponse from(Member member) {
        return new MemberInfoResponse(
                member.getNickname(),
                member.getAge(),
                member.getLocation().getAddress()
        );
    }
}

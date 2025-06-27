package com.raonhaje.memorymap.member.domain;

import com.raonhaje.memorymap.common.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "member")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;

    @Column(nullable = false, unique = true)
    private String oauthId;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false)
    private String ageRange;

    public static Member create(String oauthId, String nickname, String ageRange) {
        return Member.builder()
                .oauthId(oauthId)
                .nickname(nickname)
                .ageRange(ageRange)
                .build();
    }

    public void updateMember(String nickname, String imageUrl) {
        this.nickname = nickname;
        this.imageUrl = imageUrl;
    }
}

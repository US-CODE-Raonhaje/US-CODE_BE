package com.raonhaje.memorymap.member.domain;

import com.raonhaje.memorymap.auth.dto.KakaoUserInfo;
import com.raonhaje.memorymap.common.domain.BaseEntity;
import com.raonhaje.memorymap.like.domain.Likes;
import com.raonhaje.memorymap.post.domain.Post;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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
    private Long kakaoId;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false)
    private Integer age;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Likes> likes;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();

    private boolean additionalInfoRequired = true;

    public static Member create(Long kakaoId, String nickname, Integer age) {
        return Member.builder()
                .kakaoId(kakaoId)
                .nickname(nickname)
                .age(age)
                .build();
    }

    public static Member createFromKakao(KakaoUserInfo kakaoUserInfo) {
        Member member = new Member();
        member.kakaoId = kakaoUserInfo.id();
        member.additionalInfoRequired = true; // 최초 가입 시 추가 정보 필요
        return member;
    }

    public void updateMember(String nickname, String imageUrl) {
        this.nickname = nickname;
        this.imageUrl = imageUrl;
    }
}

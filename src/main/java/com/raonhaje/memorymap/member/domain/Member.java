package com.raonhaje.memorymap.member.domain;

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
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(unique = true)
    private String nickname;

    private Integer age;

    private String imageUrl;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Likes> likes = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();

    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private Location location;

    private boolean additionalInfoRequired;

    public static Member create(String email, String name) {
        return Member.builder()
                .email(email)
                .name(name)
                .additionalInfoRequired(true)
                .build();
    }

    public void isAdditionalInfoRequired() {
        this.additionalInfoRequired = false;
    }

    public void setAdditionalInfo(String nickname, Integer age, String imageUrl, Location location) {
        this.nickname = nickname;
        this.age = age;
        this.imageUrl = imageUrl;
        this.location = location;
        this.isAdditionalInfoRequired();
    }

    public void updateMember(String nickname, String imageUrl) {
        this.nickname = nickname;
        this.imageUrl = imageUrl;
    }
}

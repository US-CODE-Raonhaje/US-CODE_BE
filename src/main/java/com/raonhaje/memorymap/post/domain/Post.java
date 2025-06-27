package com.raonhaje.memorymap.post.domain;

import com.raonhaje.memorymap.common.domain.BaseEntity;
import com.raonhaje.memorymap.like.domain.Like;
import com.raonhaje.memorymap.member.domain.Member;
import com.raonhaje.memorymap.picture.domain.Picture;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "post")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Like> likes;

    @OneToOne(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Picture picture;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    public static Post create(String title, String content) {
        return Post.builder()
                .title(title)
                .content(content)
                .build();
    }

    public void updatePost(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }
}

package com.raonhaje.memorymap.like.domain;

import com.raonhaje.memorymap.common.domain.BaseEntity;
import com.raonhaje.memorymap.member.domain.Member;
import com.raonhaje.memorymap.post.domain.Post;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        uniqueConstraints = {@UniqueConstraint(columnNames = {"member_id", "post_id"})}
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
public class Likes extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    public static Likes create(Member member, Post post) {
        return Likes.builder()
                .member(member)
                .post(post)
                .build();
    }
}

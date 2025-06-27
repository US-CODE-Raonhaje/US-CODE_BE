package com.raonhaje.memorymap.picture.domain;

import com.raonhaje.memorymap.post.domain.Post;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "picture")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "picture_id")
    private Long pictureId;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @Column(nullable = false)
    private String imageUrl;

    public static Picture create(Post post, String imageUrl) {
        Picture picture = Picture.builder()
                .post(post)
                .imageUrl(imageUrl)
                .build();
        post.setPicture(picture);
        return picture;
    }
}

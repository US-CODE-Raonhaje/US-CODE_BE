package com.raonhaje.memorymap.post.repository;

import com.raonhaje.memorymap.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostJpaRepository extends JpaRepository<Post, Long> {
}

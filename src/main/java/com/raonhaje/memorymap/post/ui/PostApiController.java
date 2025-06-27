package com.raonhaje.memorymap.post.ui;

import com.raonhaje.memorymap.post.dto.PostCreateRequest;
import com.raonhaje.memorymap.post.dto.PostListResponse;
import com.raonhaje.memorymap.post.dto.PostResponse;
import com.raonhaje.memorymap.post.dto.PostUpdateRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
public class PostApiController implements PostApiDocs {

    @Override
    public ResponseEntity<Void> createPost(PostCreateRequest postRequest) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<Void> updatePost(Long id, PostUpdateRequest postRequest) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<Void> deletePost(Long id) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<PostResponse> getPost(Long id) {
        return ResponseEntity.ok(new PostResponse("Mock Title", "Mock Content", "mock Address"));
    }

    @Override
    public ResponseEntity<PostListResponse> getPostsByMember(Long memberId) {
        return ResponseEntity.ok(
                new PostListResponse(
                        List.of(new PostResponse("Mock Title 1", "Mock Content 1", "mock Address 1"),
                                new PostResponse("Mock Title 2", "Mock Content 2", "mock Address 2")),
                        2
                )
        );
    }
}

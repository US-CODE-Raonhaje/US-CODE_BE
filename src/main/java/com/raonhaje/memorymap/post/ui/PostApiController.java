package com.raonhaje.memorymap.post.ui;

import com.raonhaje.memorymap.common.jwt.JwtProvider;
import com.raonhaje.memorymap.post.application.PostService;
import com.raonhaje.memorymap.post.domain.Post;
import com.raonhaje.memorymap.post.dto.PostCreateRequest;
import com.raonhaje.memorymap.post.dto.PostListResponse;
import com.raonhaje.memorymap.post.dto.PostResponse;
import com.raonhaje.memorymap.post.dto.PostUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostApiController implements PostApiDocs {

    private final PostService postService;
    private final JwtProvider jwtProvider;

    @Override
    public ResponseEntity<Long> createPost(@RequestHeader(name = "Authorization") String authorizationHeader, PostCreateRequest postRequest) {
        String token = authorizationHeader.replace("Bearer ", "");
        Long kakaoId = jwtProvider.extractKakaoId(token);

        Post post = postService.createPost(kakaoId, postRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(post.getPostId());
    }

    @Override
    public ResponseEntity<Void> updatePost(@RequestHeader(name = "Authorization") String authorizationHeader, Long id, PostUpdateRequest postRequest) {
        String token = authorizationHeader.replace("Bearer ", "");
        Long kakaoId = jwtProvider.extractKakaoId(token);

        postService.updatePost(kakaoId, id, postRequest);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<Void> deletePost(@RequestHeader(name = "Authorization") String authorizationHeader, Long id) {
        String token = authorizationHeader.replace("Bearer ", "");
        Long kakaoId = jwtProvider.extractKakaoId(token);

        postService.deletePost(kakaoId, id);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<PostResponse> getPost(@RequestHeader(name = "Authorization") String authorizationHeader, Long id) {
        String token = authorizationHeader.replace("Bearer ", "");
        Long kakaoId = jwtProvider.extractKakaoId(token);

        Post post = postService.getPost(kakaoId, id);
        PostResponse postResponse = PostResponse.from(post);

        return ResponseEntity.ok(postResponse);
    }

    @Override
    public ResponseEntity<PostListResponse> getPostsByMember(@RequestHeader(name = "Authorization") String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "");
        Long kakaoId = jwtProvider.extractKakaoId(token);

        List<Post> posts = postService.getPostsByMember(kakaoId);
        List<PostResponse> postResponses = posts.stream()
                .map(PostResponse::from)
                .toList();
        PostListResponse postListResponse = new PostListResponse(postResponses, posts.size());

        return ResponseEntity.ok(postListResponse);
    }
}

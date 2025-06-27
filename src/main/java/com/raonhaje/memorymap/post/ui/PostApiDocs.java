package com.raonhaje.memorymap.post.ui;

import com.raonhaje.memorymap.post.dto.PostCreateRequest;
import com.raonhaje.memorymap.post.dto.PostListResponse;
import com.raonhaje.memorymap.post.dto.PostResponse;
import com.raonhaje.memorymap.post.dto.PostUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "게시글", description = "게시글 관련 API 문서입니다.")
public interface PostApiDocs {

    @Operation(summary = "게시글 작성", description = "새로운 게시글을 작성합니다.", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "게시글 작성 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "401", description = "인증 실패")
    })
    @PostMapping("/posts")
    ResponseEntity<Void> createPost(@RequestBody PostCreateRequest postRequest);

    @Operation(summary = "게시글 수정", description = "기존 게시글을 수정합니다.", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "게시글 수정 성공"),
            @ApiResponse(responseCode = "404", description = "게시글 없음"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "401", description = "인증 실패")
    })
    @PutMapping("/posts/{id}")
    ResponseEntity<Void> updatePost(@PathVariable Long id, @RequestBody PostUpdateRequest postRequest);

    @Operation(summary = "게시글 삭제", description = "기존 게시글을 삭제합니다.", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "게시글 삭제 성공"),
            @ApiResponse(responseCode = "404", description = "게시글 없음"),
            @ApiResponse(responseCode = "401", description = "인증 실패")
    })
    @DeleteMapping("/posts/{id}")
    ResponseEntity<Void> deletePost(@PathVariable Long id);

    @Operation(summary = "게시글 단건 조회", description = "ID를 기반으로 게시글을 조회합니다.", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "게시글 조회 성공",
                    content = @Content(schema = @Schema(implementation = PostResponse.class))),
            @ApiResponse(responseCode = "404", description = "게시글 없음"),
            @ApiResponse(responseCode = "401", description = "인증 실패")
    })
    @GetMapping("/posts/{id}")
    ResponseEntity<PostResponse> getPost(@PathVariable Long id);

    @Operation(summary = "회원의 게시글 목록 조회", description = "특정 회원이 작성한 게시글 목록을 조회합니다.", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공",
                    content = @Content(schema = @Schema(implementation = PostListResponse.class))),
            @ApiResponse(responseCode = "401", description = "인증 실패")
    })
    @GetMapping("/members/{memberId}/posts")
    ResponseEntity<PostListResponse> getPostsByMember(@PathVariable Long memberId);
}

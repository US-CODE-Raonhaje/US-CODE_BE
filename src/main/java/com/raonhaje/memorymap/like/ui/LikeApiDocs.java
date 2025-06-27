package com.raonhaje.memorymap.like.ui;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Tag(name = "좋아요", description = "좋아요 관련 API 문서입니다.")
public interface LikeApiDocs {

    @Operation(summary = "게시물 좋아요 추가", description = "특정 게시물에 좋아요를 추가합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "404", description = "게시물 없음"),
            @ApiResponse(responseCode = "401", description = "인증 실패")
    })
    @PostMapping("/{postId}")
    ResponseEntity<Void> like(@PathVariable Long postId);

    @Operation(summary = "게시물 좋아요 취소", description = "특정 게시물에 대한 좋아요를 취소합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "성공"),
            @ApiResponse(responseCode = "404", description = "게시물 없음"),
            @ApiResponse(responseCode = "401", description = "인증 실패")
    })
    @PostMapping("/{postId}/cancel")
    ResponseEntity<Void> cancelLike(@PathVariable Long postId);
}

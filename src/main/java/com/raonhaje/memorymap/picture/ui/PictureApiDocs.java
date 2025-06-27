package com.raonhaje.memorymap.picture.ui;

import com.raonhaje.memorymap.picture.dto.PictureListResponse;
import com.raonhaje.memorymap.picture.dto.PictureResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@Tag(name = "사진", description = "사진 관련 API 문서입니다.")
public interface PictureApiDocs {

    @Operation(summary = "사진 조회", description = "사진을 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "404", description = "사진 없음")
    })
    @GetMapping("/{id}")
    ResponseEntity<PictureResponse> getPicture(Long id);

    @Operation(summary = "사진 목록 조회", description = "사진 목록을 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "404", description = "사진 목록 없음")
    })
    @GetMapping
    ResponseEntity<PictureListResponse> getPictures();
}

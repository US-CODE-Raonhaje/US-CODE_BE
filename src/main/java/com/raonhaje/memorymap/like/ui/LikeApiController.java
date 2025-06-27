package com.raonhaje.memorymap.like.ui;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/likes")
public class LikeApiController implements LikeApiDocs {
    
    @Override
    public ResponseEntity<Void> like(Long postId) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<Void> cancelLike(Long postId) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}

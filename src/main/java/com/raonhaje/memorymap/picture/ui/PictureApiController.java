package com.raonhaje.memorymap.picture.ui;

import com.raonhaje.memorymap.picture.dto.PictureListResponse;
import com.raonhaje.memorymap.picture.dto.PictureResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pictures")
public class PictureApiController implements PictureApiDocs {
    
    @Override
    public ResponseEntity<PictureResponse> getPicture(Long id) {
        return ResponseEntity.ok(
                new PictureResponse("mockImageUrl")
        );
    }

    @Override
    public ResponseEntity<PictureListResponse> getPictures() {
        return ResponseEntity.ok( new PictureListResponse(
                List.of(
                        new PictureResponse("mockImageUrl 1"),
                        new PictureResponse("mockImageUrl 2")
                ),
                2)
        );
    }
}

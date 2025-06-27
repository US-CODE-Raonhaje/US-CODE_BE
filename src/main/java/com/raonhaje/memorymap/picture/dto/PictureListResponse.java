package com.raonhaje.memorymap.picture.dto;

import java.util.List;

public record PictureListResponse(

        List<PictureResponse> pictures,
        int totalCount
) {
}

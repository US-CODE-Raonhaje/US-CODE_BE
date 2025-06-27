package com.raonhaje.memorymap.member.ui;

import com.raonhaje.memorymap.member.dto.MemberInfoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/members")
public class MemberApiController implements MemberApiDocs {

    @Override
    public ResponseEntity<MemberInfoResponse> getMyInfo() {
        return ResponseEntity.ok(new MemberInfoResponse("mockUserId", 25, "mockLocationName"));
    }
}

package com.raonhaje.memorymap.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    MEMBER_NOT_FOUND("회원이 존재하지 않습니다.", HttpStatus.NOT_FOUND.value()),
    EXISTING_MEMBER("이미 존재하는 회원입니다.", HttpStatus.BAD_REQUEST.value()),


    INVALID_INPUT_VALUE("잘못된 입력입니다.", HttpStatus.BAD_REQUEST.value()),
    METHOD_NOT_ALLOWED("허용되지 않은 HTTP 메소드입니다.", HttpStatus.METHOD_NOT_ALLOWED.value()),
    UNSUPPORTED_MEDIA_TYPE("지원하지 않는 미디어 타입입니다.", HttpStatus.UNSUPPORTED_MEDIA_TYPE.value()),
    INTERNAL_SERVER_ERROR("서버 오류입니다.", HttpStatus.INTERNAL_SERVER_ERROR.value()),
    BUSINESS_ERROR("비즈니스 로직 오류입니다.", HttpStatus.BAD_REQUEST.value());

    private final String message;
    private final int status;
}

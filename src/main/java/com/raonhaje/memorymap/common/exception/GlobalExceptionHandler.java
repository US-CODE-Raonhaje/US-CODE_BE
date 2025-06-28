package com.raonhaje.memorymap.common.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(HttpServletRequest request, BusinessException ex) {
        log.warn("BusinessException: {}", ex.getMessage());
        ErrorCode code = ex.getErrorCode();
        ErrorResponse error = ErrorResponse.of(code, request.getRequestURI());
        return new ResponseEntity<>(error, HttpStatus.valueOf(code.getStatus()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(HttpServletRequest request, MethodArgumentNotValidException ex) {
        String message = "유효성 검사에 실패하였습니다.";
        if (!ex.getBindingResult().getFieldErrors().isEmpty()) {
            message = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        }
        log.warn("MethodArgumentNotValidException: {}", message);
        ErrorResponse error = ErrorResponse.builder()
                .message(message)
                .status(ErrorCode.INVALID_INPUT_VALUE.getStatus())
                .path(request.getRequestURI())
                .timestamp(Instant.now())
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(org.springframework.validation.BindException.class)
    public ResponseEntity<ErrorResponse> handleBindException(HttpServletRequest request, BindException ex) {
        String message = "유효성 검사에 실패하였습니다.";
        if (!ex.getBindingResult().getFieldErrors().isEmpty()) {
            message = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        }
        log.warn("BindException: {}", message);
        ErrorResponse error = ErrorResponse.builder()
                .message(message)
                .status(ErrorCode.INVALID_INPUT_VALUE.getStatus())
                .path(request.getRequestURI())
                .timestamp(Instant.now())
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleMediaTypeNotSupported(HttpServletRequest request, HttpMediaTypeNotSupportedException ex) {
        log.warn("HttpMediaTypeNotSupportedException: {}", ex.getMessage());
        ErrorResponse error = ErrorResponse.of(ErrorCode.UNSUPPORTED_MEDIA_TYPE, request.getRequestURI());
        return new ResponseEntity<>(error, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleMethodNotSupported(HttpServletRequest request, HttpRequestMethodNotSupportedException ex) {
        log.warn("HttpRequestMethodNotSupportedException: {}", ex.getMessage());
        ErrorResponse error = ErrorResponse.of(ErrorCode.METHOD_NOT_ALLOWED, request.getRequestURI());
        return new ResponseEntity<>(error, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgument(HttpServletRequest request, Exception ex) {
        log.warn("IllegalArgumentException: {}", ex.getMessage());
        ErrorResponse error = ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE, request.getRequestURI());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(HttpServletRequest request, Exception ex) {
        log.error("Unhandled Exception: ", ex);
        ErrorResponse error = ErrorResponse.of(ErrorCode.INTERNAL_SERVER_ERROR, request.getRequestURI());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

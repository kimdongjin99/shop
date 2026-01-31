package com.example.shop.global.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponse {
    private int status;      // HTTP 상태 번호
    private String message;   // 에러 메시지
}
package com.example.shop.User; // 폴더명이 대문자 User이므로 맞춰줍니다.

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginRequestDto {
    private String loginId;
    private String password;
}
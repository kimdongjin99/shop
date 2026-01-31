package com.example.shop.User;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid; // [추가] 검증을 위해 필요함

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    // @RequestBody 앞에 @Valid를 붙여주는 게 핵심입니다!
    public String signUp(@Valid @RequestBody UserRequestDto dto) {
        userService.signUp(dto);
        return "회원가입 성공!";
    }
}
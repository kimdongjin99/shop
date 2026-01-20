package com.example.shop.User;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.shop.User.UserRequestDto;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public String signUp(@RequestBody UserRequestDto dto) {
        userService.signUp(dto);
        return "회원가입 성공!";
    }
}
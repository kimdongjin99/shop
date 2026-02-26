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
    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDto dto) {
        userService.login(dto);
        return "로그인 성공!"; // 엔티티 대신 간단한 성공 메시지만 반환해 보세요.
    }
    @PutMapping("/{id}")
    public String update(@PathVariable Long id, @RequestBody UserRequestDto dto) {
        userService.updateUser(id, dto);
        return "회원 정보가 수정되었습니다.";
    }
    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.findOne(id);
    }
}
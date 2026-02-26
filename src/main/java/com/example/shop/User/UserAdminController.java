package com.example.shop.User;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController("userAdminController")
@RequestMapping("/api/admin/users") // 주소를 아예 /users까지 포함시켰습니다.
@RequiredArgsConstructor
public class UserAdminController {

    private final UserAdminService adminService;
    private final UserService userService; // 필드를 위로 모았습니다.

    // 전체 회원 조회 API -> 이제 주소는 /api/admin/users 입니다.
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(adminService.findAllUsers());
    }

    // 개별 회원 조회 API -> 이제 주소는 /api/admin/users/{id} 입니다.
    @GetMapping("/{id}")
    public String getUser(@PathVariable Long id) {
        User user = userService.findOne(id);
        return user.getName() + "님 조회 성공!";
    }

    // 회원 강제 삭제 API -> 이제 주소는 /api/admin/users/{id} 입니다.
    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeUser(@PathVariable Long id) {
        adminService.deleteUser(id);
        return ResponseEntity.ok("회원이 삭제되었습니다.");
    }
}
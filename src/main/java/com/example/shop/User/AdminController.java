package com.example.shop.User;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    // 전체 회원 조회 API
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(adminService.findAllUsers());
    }

    // 회원 강제 삭제 API
    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> removeUser(@PathVariable Long id) {
        adminService.deleteUser(id);
        return ResponseEntity.ok("회원이 삭제되었습니다.");
    }
}
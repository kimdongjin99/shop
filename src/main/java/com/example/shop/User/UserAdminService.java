package com.example.shop.User;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserAdminService {

    private final UserRepository userRepository;

    // 모든 회원 정보 조회
    @Transactional(readOnly = true)
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    // 특정 회원 강제 탈퇴 (삭제)
    @Transactional
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
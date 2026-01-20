package com.example.shop.User;

import com.example.shop.User.User; // 👈 이거 추가
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLoginId(String loginId); // 로그인 아이디로 유저 찾기
    boolean existsByLoginId(String loginId);    // 아이디 중복 체크용
}
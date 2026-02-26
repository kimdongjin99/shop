package com.example.shop.User;

import com.example.shop.global.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*; // 이렇게 하면 lombok의 모든 기능을 한 번에 가져옵니다.
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder; // 에러 대비용

@Entity
@Table(name = "users") // DB 예약어와 겹칠 수 있으니 users로 지정하는 게 안전해
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA를 위해 기본 생성자는 필수!
@AllArgsConstructor
@Builder
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String loginId; // 로그인용 아이디

    @Column(nullable = false)
    private String password; // 암호화된 비번 저장

    @Column(nullable = false)
    private String name;
    private String email;

    @Enumerated(EnumType.STRING) // Enum 이름을 문자열로 DB에 저장해 (USER, ADMIN)
    @Column(nullable = false)
    private Role role;

    @Builder.Default
    private boolean isDeleted = false; // 회원 탈퇴 여부 (Soft Delete용)

    // 비즈니스 로직: 정보 수정
    public void updateInfo(String loginId, String password, String name) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
    }

    // 비즈니스 로직: 탈퇴 처리
    public void withdraw() {
        this.isDeleted = true;
    }
}
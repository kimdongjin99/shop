package com.example.shop.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    private Long id;
    private String loginId;
    private String name;
    private Role role;

    // Entity를 DTO로 변환해주는 정적 메서드 (선택 사항이나 편리함)
    public static UserResponseDto from(User user) {
        return new UserResponseDto(
                user.getId(),
                user.getLoginId(),
                user.getName(),
                user.getRole()
        );
    }
}
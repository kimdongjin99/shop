package com.example.shop.User;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDto {
    private String loginId;
    private String password;
    private String name;
    // 초기 가입 시에는 보통 일반 유저로 가입하니까 Role은 여기서 안 받아도 돼
}
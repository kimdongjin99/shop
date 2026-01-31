package com.example.shop.User;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter              // 데이터 수정을 위해 추가
@NoArgsConstructor   // 기본 생성자 (Jackson 라이브러리용)
@AllArgsConstructor  // 모든 필드 생성자
public class UserRequestDto {

    @NotBlank(message = "아이디를 입력해 주세요.")
    @Size(min = 4, max = 12, message = "아이디는 4~12자 사이여야 합니다.")
    private String loginId;

    @NotBlank(message = "비밀번호를 입력해 주세요.")
    @Size(min = 8, message = "비밀번호는 최소 8자 이상이어야 합니다.")
    private String password;

    @NotBlank(message = "이름은 필수 항목입니다.")
    private String name;

    @NotBlank(message = "전화번호를 입력해 주세요.")
    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "올바른 전화번호 형식(010-0000-0000)이 아닙니다.")
    private String phone;

    @NotBlank(message = "주소를 입력해 주세요.")
    private String address;

    @NotBlank(message = "이메일을 입력해 주세요.")
    @Email(message = "유효한 이메일 형식이 아닙니다.")
    private String email;
}
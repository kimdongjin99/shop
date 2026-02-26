package com.example.shop.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public Long signUp(UserRequestDto dto) {
        // 1. 아이디 중복 체크
        if (userRepository.existsByLoginId(dto.getLoginId())) {
            throw new RuntimeException("이미 존재하는 아이디입니다.");
        }

        // 2. 비밀번호 암호화 후 유저 생성
        User user = User.builder()
                .loginId(dto.getLoginId())
                .password(passwordEncoder.encode(dto.getPassword())) //  암호화 핵심
                .name(dto.getName())
                .role(Role.USER) // 기본 권한은 USER로 설정
                .build();

        return userRepository.save(user).getId();
    }

    public User findOne(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 회원이 없습니다."));
    }

    public User login(LoginRequestDto dto) {
        // 1. 아이디로 유저 찾기
        User user = userRepository.findByLoginId(dto.getLoginId())
                .orElseThrow(() -> new RuntimeException("아이디가 존재하지 않습니다."));

        // 2. 비밀번호 일치 확인
        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        return user;
    }

    @Transactional
    public void updateUser(Long id, UserRequestDto dto) {
        // 1. 수정할 유저 찾기
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 회원이 존재하지 않습니다."));

        // 2. 엔티티의 updateInfo 메서드 호출 (비밀번호는 암호화해서 전달)
        user.updateInfo(
                dto.getLoginId(),
                passwordEncoder.encode(dto.getPassword()),
                dto.getName()
        );
    }
}
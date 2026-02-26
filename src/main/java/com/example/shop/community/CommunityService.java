package com.example.shop.community;

import com.example.shop.User.User;
import com.example.shop.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommunityService {

    private final CommunityRepository communityRepository;
    private final UserRepository userRepository; // 3. 이게 있어야 findByName을 씁니다!

    @Transactional
    public void save(CommunityRequestDto dto) {
        // 이름으로 유저를 찾고, 없으면 에러 발생
        User user = userRepository.findByName(dto.getAuthorName())
                .orElseThrow(() -> new RuntimeException("해당 이름의 유저가 없습니다."));

        Community community = Community.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .postType(dto.getPostType())
                .author(user) // 4. 이제 User 객체가 정상적으로 들어갑니다.
                .build();

        communityRepository.save(community);
    }

    public List<Community> findAll() {
        return communityRepository.findAll();
    }

    public Community findOne(Long id) {
        return communityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
    }

    @Transactional
    public void delete(Long id) {
        communityRepository.deleteById(id);
    }
}
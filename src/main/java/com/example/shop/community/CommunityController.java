package com.example.shop.community;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/community")
@RequiredArgsConstructor
public class CommunityController {

    private final CommunityService communityService;

    // 1. 전체 목록 조회
    @GetMapping
    public ResponseEntity<List<Community>> getAllPosts() {
        return ResponseEntity.ok(communityService.findAll());
    }

    // 2. 상세 조회
    @GetMapping("/{id}")
    public ResponseEntity<Community> getCommunityDetail(@PathVariable Long id) {
        Community community = communityService.findOne(id);
        return ResponseEntity.ok(community);
    }


    // 3. 게시글 등록
    @PostMapping
    public ResponseEntity<String> createPost(@RequestBody CommunityRequestDto dto) {
        // 서비스의 save 메서드를 호출 (서비스에 save가 구현되어 있어야 함)
        communityService.save(dto);
        return ResponseEntity.ok("게시글이 성공적으로 등록되었습니다!");
    }

    // 4. 게시글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        communityService.delete(id);
        return ResponseEntity.ok().build();
    }
}
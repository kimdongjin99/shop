package com.example.shop.community;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommunityRepository extends JpaRepository<Community, Long> {
    // 특정 타입의 게시글만 조회 (명세서의 후기/Q&A 조회를 위함)
    List<Community> findByPostType(PostType postType);
}
package com.example.shop.community;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommunityRequestDto {
    private Long id;
    private String title;
    private String content;
    private PostType postType; // NOTICE, REVIEW, QNA 등
    private String authorName;


    // 이 데이터를 가지고 실제 Community 엔티티로 변환할 때 사용합니다.
    public Community toEntity() {
        // 나중에 Service에서 작성자(User)를 추가로 연결해주어야 합니다.
        return null; // 지금은 뼈대만 잡으므로 null 처리하거나 나중에 로직을 채웁니다.
    }
}
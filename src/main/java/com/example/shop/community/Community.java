package com.example.shop.community;

import com.example.shop.global.common.BaseTimeEntity;
import com.example.shop.User.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Community extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    private PostType postType; // NOTICE, REVIEW, QNA 등 구분

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User author; // 작성자 연결

    // 게시글 수정을 위한 메서드
    public void update(String title, String content, PostType postType) {
        this.title = title;
        this.content = content;
        this.postType = postType;
    }
}
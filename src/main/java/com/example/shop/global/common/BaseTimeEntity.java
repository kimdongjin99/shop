package com.example.shop.global.common;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass // 이 클래스를 상속받는 엔티티들이 아래 필드들을 컬럼으로 인식하게 합니다.
@EntityListeners(AuditingEntityListener.class) // 시간에 따라 자동으로 값을 넣어주는 기능입니다.
public abstract class BaseTimeEntity {

    @CreatedDate // 데이터 생성 시 시간 자동 저장
    private LocalDateTime createdAt;

    @LastModifiedDate // 데이터 수정 시 시간 자동 저장
    private LocalDateTime modifiedAt;
}
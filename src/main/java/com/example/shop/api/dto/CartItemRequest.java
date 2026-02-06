package com.example.shop.api.dto; // 패키지명 주의

import lombok.Data;

@Data
public class CartItemRequest {
    private Long productId;
    private int count;
}
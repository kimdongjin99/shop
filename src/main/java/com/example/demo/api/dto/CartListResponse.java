package com.example.demo.api.dto;

import lombok.Data;

@Data
public class CartListResponse {
    private Long id;          // 장바구니 아이템 ID (삭제할 때 필요)
    private String productName;
    private int price;
    private int count;

    public CartListResponse(Long id, String productName, int price, int count) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.count = count;
    }
}
package com.example.shop.api.dto;

import lombok.Data;

@Data
public class ProductFormRequest {
    private String name;
    private int price;
    private String description;
    private int stockQuantity;
    private String status; // 판매상태 (SELL, STOP)
}
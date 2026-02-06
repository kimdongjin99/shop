package com.example.shop.api.dto;

import lombok.Data;

@Data
public class OrderCreateRequest {
    private Long productId;
    private int count;
    // 주소 정보 (간단하게)
    private String city;
    private String street;
    private String zipcode;
}
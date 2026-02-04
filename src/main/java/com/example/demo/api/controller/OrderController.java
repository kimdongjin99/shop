package com.example.demo.api.controller;

import com.example.demo.api.dto.OrderCreateRequest;
import com.example.demo.api.service.OrderService;
import com.example.demo.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final Long TEMP_USER_ID = 1L; // 임시 유저

    // 1. 주문하기
    @PostMapping
    public String createOrder(@RequestBody OrderCreateRequest request) {
        orderService.order(TEMP_USER_ID, request);
        return "주문 성공";
    }

    // 2. 주문 목록 조회
    @GetMapping
    public List<Order> getOrders() {
        return orderService.findOrders(TEMP_USER_ID);
        // 실제로는 DTO로 변환해서 반환해야 하지만, 연습용으로 Entity 반환
    }

    // 3. 주문 취소
    @PostMapping("/{orderId}/cancel")
    public String cancelOrder(@PathVariable Long orderId) {
        orderService.cancelOrder(orderId);
        return "취소 성공";
    }
}
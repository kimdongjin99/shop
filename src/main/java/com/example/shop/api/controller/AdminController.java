package com.example.shop.api.controller;

import com.example.shop.api.dto.ProductFormRequest;
import com.example.shop.api.service.AdminService;
import com.example.shop.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    // 1. 상품 등록
    @PostMapping("/products")
    public String createProduct(@RequestBody ProductFormRequest request) {
        adminService.createProduct(request);
        return "상품 등록 완료";
    }

    // 2. 상품 삭제
    @DeleteMapping("/products/{productId}")
    public String deleteProduct(@PathVariable Long productId) {
        adminService.deleteProduct(productId);
        return "상품 삭제 완료";
    }

    // 3. 전체 주문 조회
    @GetMapping("/orders")
    public List<Order> getAllOrders() {
        return adminService.getAllOrders();
    }

    // 4. 배송 완료 처리
    @PostMapping("/orders/{orderId}/complete")
    public String completeOrder(@PathVariable Long orderId) {
        adminService.completeDelivery(orderId);
        return "배송 완료 처리됨";
    }


}
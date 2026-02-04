package com.example.demo.api.service;

import com.example.demo.api.dto.ProductFormRequest;
import com.example.demo.domain.*;
import com.example.demo.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    // 1. 상품 등록
    public Long createProduct(ProductFormRequest request) {
        Product product = new Product(
                request.getName(),
                request.getPrice(),
                request.getDescription(),
                request.getStockQuantity()
        );
        productRepository.save(product);
        return product.getId();
    }

    // 2. 상품 삭제
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    // 3. 주문 배송 상태 변경 (준비 -> 완료)
    public void completeDelivery(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow();

        // 배송 정보가 있으면 상태를 COMP(완료)로 변경
        if (order.getDelivery() != null) {
            order.getDelivery().setStatus("COMP");
        }
    }

    // 4. 전체 주문 조회 (관리자용)
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
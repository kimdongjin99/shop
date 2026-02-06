package com.example.demo.api.service;

import com.example.demo.api.dto.OrderCreateRequest;
import com.example.demo.domain.*;
import com.example.demo.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    // 1. 주문하기
    @Transactional
    public Long order(Long userId, OrderCreateRequest request) {
        // 엔티티 조회
        User user = userRepository.findById(userId).orElseThrow();
        Product product = productRepository.findById(request.getProductId()).orElseThrow();

        // 배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setCity(request.getCity());
        delivery.setStreet(request.getStreet());
        delivery.setZipcode(request.getZipcode());
        delivery.setStatus("READY");

        // 주문상품 생성 (이때 재고가 줄어듭니다)
        OrderItem orderItem = OrderItem.createOrderItem(product, product.getPrice(), request.getCount());

        // 주문 생성
        Order order = Order.createOrder(user, delivery, orderItem);

        // 주문 저장 (Cascade 옵션 때문에 OrderItem, Delivery도 같이 저장됨)
        orderRepository.save(order);

        return order.getId();
    }

    // 2. 주문 취소
    @Transactional
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow();
        order.cancel(); // 재고 복구 로직 실행
    }

    // 3. 주문 목록 조회
    public List<Order> findOrders(Long userId) {
        return orderRepository.findByUserId(userId);
    }
}
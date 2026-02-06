package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@NoArgsConstructor
public class Order {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate; // 주문 시간

    private String status; // ORDER, CANCEL

    // 주문 생성 메서드 (복잡한 생성 로직을 여기서 처리)
    public static Order createOrder(User user, Delivery delivery, OrderItem... orderItems) {
        Order order = new Order();
        order.user = user;
        order.delivery = delivery;
        for (OrderItem orderItem : orderItems) {
            order.orderItems.add(orderItem);
            orderItem.setOrder(order);
        }
        order.status = "ORDER";
        order.orderDate = LocalDateTime.now();
        return order;
    }

    // 주문 취소
    public void cancel() {
        if (delivery.getStatus() != null && delivery.getStatus().equals("COMP")) {
            throw new IllegalStateException("이미 배송완료된 상품은 취소가 불가능합니다.");
        }
        this.status = "CANCEL";
        for (OrderItem orderItem : orderItems) {
            orderItem.cancel();
        }
    }
}
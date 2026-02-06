package com.example.shop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class OrderItem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice; // 주문 가격
    private int count;      // 주문 수량

    // 생성 메서드
    public static OrderItem createOrderItem(Product product, int orderPrice, int count) {
        OrderItem orderItem = new OrderItem();
        orderItem.product = product;
        orderItem.orderPrice = orderPrice;
        orderItem.count = count;

        product.removeStock(count); // 주문 시 재고 감소!
        return orderItem;
    }

    // 주문 취소 시 재고 원상복구
    public void cancel() {
        getProduct().addStock(count);
    }

    // 주문 설정 (Order에서 호출)
    public void setOrder(Order order) {
        this.order = order;
    }
}
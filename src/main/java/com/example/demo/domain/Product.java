package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int price;
    private String description;
    private int stockQuantity;

    // 생성자: 객체를 처음 만들 때 사용하는 것
    public Product(String name, int price, String description, int stockQuantity) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.stockQuantity = stockQuantity;
    } // <--- ★★★ 생성자는 여기서 끝나야 합니다! ★★★

    // 재고 증가 (주문 취소 시) - 생성자 밖으로 나왔습니다
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    // 재고 감소 (주문 시) - 생성자 밖으로 나왔습니다
    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0) {
            throw new IllegalStateException("재고가 부족합니다.");
        }
        this.stockQuantity = restStock;
    }
}
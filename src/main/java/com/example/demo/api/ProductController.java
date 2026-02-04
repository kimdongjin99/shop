package com.example.demo.api;

import com.example.demo.domain.Product;
import com.example.demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products") // 모든 주소 앞에 /api/products가 자동으로 붙음
public class ProductController {

    private final ProductRepository productRepository;

    // 1. 전체 상품 목록 조회
    // 접속 주소: localhost:8080/api/products
    @GetMapping
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    // 2. 상품 상세 조회 (ID로 조회)
    // 접속 주소 예시: localhost:8080/api/products/1
    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("상품이 없습니다."));
    }

    // 3. 상품 검색 (이름으로 단순 검색)
    // 접속 주소 예시: localhost:8080/api/products/search?keyword=나이키
    @GetMapping("/search")
    public List<Product> searchProducts(@RequestParam String keyword) {
        // 원래는 Repository에 검색 메서드를 만들어야 하지만, 초보자용으로 간단하게 자바 코드로 필터링 구현
        List<Product> all = productRepository.findAll();
        return all.stream()
                .filter(p -> p.getName().contains(keyword))
                .collect(Collectors.toList());
    }
}
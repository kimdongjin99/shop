package com.example.shop.api;

import com.example.shop.domain.Product;
import com.example.shop.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final ProductRepository productRepository;

        public void dbInit() {
            Product product1 = new Product("나이키 운동화", 150000, "편안한 러닝화", 100);
            Product product2 = new Product("아디다스 티셔츠", 50000, "기능성 반팔티", 200);
            productRepository.save(product1);
            productRepository.save(product2);

        }
    }
}
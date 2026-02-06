package com.example.demo.api.service;

import com.example.demo.api.dto.*;
import com.example.demo.domain.*;
import com.example.demo.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    // 1. 장바구니 조회
    public List<CartListResponse> getCartList(Long userId) {
        Cart cart = cartRepository.findByUserId(userId).orElse(null);
        List<CartListResponse> result = new ArrayList<>();

        if (cart != null) {
            for (CartItem item : cart.getItems()) {
                result.add(new CartListResponse(
                        item.getId(),
                        item.getProduct().getName(),
                        item.getProduct().getPrice(),
                        item.getCount()
                ));
            }
        }
        return result;
    }

    // 2. 장바구니에 상품 담기
    public void addCart(Long userId, CartItemRequest request) {
        // 유저 확인 및 장바구니 조회 (없으면 생성)
        User user = userRepository.findById(userId).orElseThrow();
        Cart cart = cartRepository.findByUserId(userId)
                .orElseGet(() -> cartRepository.save(Cart.createCart(user)));

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("상품 없음"));

        // 이미 담겨있는지 확인
        CartItem cartItem = cartItemRepository.findByCartIdAndProductId(cart.getId(), product.getId())
                .orElse(null);

        if (cartItem != null) {
            // 이미 있으면 수량 추가
            cartItem.addCount(request.getCount());
        } else {
            // 없으면 새로 추가
            cartItem = CartItem.createCartItem(cart, product, request.getCount());
            cartItemRepository.save(cartItem);
        }
    }

    // 3. 장바구니 아이템 삭제
    public void deleteCartItem(Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }
}
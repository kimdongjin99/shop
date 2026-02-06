package com.example.shop.api.controller; // 패키지 위치 주의 (com.example.shop.api.controller)

import com.example.shop.api.dto.CartItemRequest;
import com.example.shop.api.dto.CartListResponse;
import com.example.shop.api.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/carts")
public class CartController {

    private final CartService cartService;

    // 원래는 로그인한 유저 ID를 받아야 하지만, 연습용으로 1번 유저라고 가정
    private final Long TEMP_USER_ID = 1L;

    // 1. 장바구니 조회
    @GetMapping
    public List<CartListResponse> getCartList() {
        return cartService.getCartList(TEMP_USER_ID);
    }

    // 2. 장바구니 담기
    @PostMapping
    public String addCart(@RequestBody CartItemRequest request) {
        cartService.addCart(TEMP_USER_ID, request);
        return "장바구니 담기 성공";
    }

    // 3. 장바구니 아이템 삭제
    @DeleteMapping("/{cartItemId}")
    public String deleteCart(@PathVariable Long cartItemId) {
        cartService.deleteCartItem(cartItemId);
        return "삭제 성공";
    }
}
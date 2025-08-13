package com.shoplyft.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.shoplyft.backend.controller.request.CartCreateRequest;
import com.shoplyft.backend.controller.request.CartUpdateRequest;
import com.shoplyft.backend.controller.response.CartResponse;
import com.shoplyft.backend.service.CartService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<CartResponse>> getCart(@PathVariable Long userId) {
        return ResponseEntity.ok(cartService.getCartByUserId(userId).stream().map(CartResponse::toResponse).toList());
    }

    // Post endpoint to add a book to the cart can be added here
    @PostMapping("/{userId}/items")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCartItem(@PathVariable Long userId, @RequestBody @Valid CartCreateRequest cartRequest) {
        cartService.createCart(userId, cartRequest.getItemId());
    }

    @PutMapping("/{userId}/items/{itemId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateCartItem(@PathVariable Long userId, @PathVariable Long itemId, @RequestBody @Valid CartUpdateRequest cartRequest) {
        cartService.updateCart(userId, itemId, cartRequest.getQuantity());
    }

    @DeleteMapping("/{userId}/items/{itemId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCartItem(@PathVariable Long userId, @PathVariable Long itemId) {
        cartService.delete(userId, itemId);
    }
}

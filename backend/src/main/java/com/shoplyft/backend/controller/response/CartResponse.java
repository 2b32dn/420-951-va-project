package com.shoplyft.backend.controller.response;

import com.shoplyft.backend.model.Cart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CartResponse {

    UserResponse user;
    ItemResponse item;
    int quantity;

    public static CartResponse toResponse(Cart cart) {
        return new CartResponse(UserResponse.toResponse(cart.getUser()),
                ItemResponse.toResponse(cart.getItem()),
                cart.getQuantity());
    }
}

package com.shoplyft.backend.controller.response;

import com.shoplyft.backend.model.Item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ItemResponse {

    private String item;
    private String itemCategory;
    private String description;
    private double price;
    private int quantity;

    public static ItemResponse toResponse(Item item) {
        return new ItemResponse(
                item.getItem(),
                item.getItemCategory(),
                item.getDescription(),
                item.getPrice(),
                item.getQuantity());
    }
}

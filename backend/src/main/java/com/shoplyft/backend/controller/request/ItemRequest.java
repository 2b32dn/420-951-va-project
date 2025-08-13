package com.shoplyft.backend.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ItemRequest {

    @NotBlank
    @Size(min = 2, max = 100, message = "Item name must be between 2 and 100 characters")
    private String item;
    @NotNull(groups = UpdateGroup.class)
    private String item_category;
    @NotNull(groups = UpdateGroup.class)
    private String description;
    @NotNull(groups = UpdateGroup.class)
    private double price;
    @NotNull(groups = UpdateGroup.class)
    private int quantity;
}

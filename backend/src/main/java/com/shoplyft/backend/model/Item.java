package com.shoplyft.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "items")
@Getter
@Setter
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item")           // was 'item'
    private String item;

    @Column(name = "item_category")  // was 'item_category'
    private String itemCategory;

    private String description;
    private double price;
    private int quantity;

    public Item(String item, String itemCategory, String description, double price, int quantity) {
        this.item = item;
        this.itemCategory = itemCategory;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }
}

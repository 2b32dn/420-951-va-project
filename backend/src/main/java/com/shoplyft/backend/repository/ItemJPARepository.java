package com.shoplyft.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shoplyft.backend.model.Item;

public interface ItemJPARepository extends JpaRepository<Item, Long> {

    // Additional query methods can be defined here if needed
    List<Item> findByItemContainingIgnoreCase(String item);
}

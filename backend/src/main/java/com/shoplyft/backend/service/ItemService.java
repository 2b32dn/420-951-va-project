package com.shoplyft.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.shoplyft.backend.model.Item;
import com.shoplyft.backend.repository.ItemJPARepository;

import lombok.NonNull;

@Service
public class ItemService {

    private final ItemJPARepository itemJPARepository;

    public ItemService(@NonNull ItemJPARepository itemJPARepository) {
        this.itemJPARepository = itemJPARepository;
    }

    public List<Item> getItems() {
        return itemJPARepository.findAll();
    }

    public Optional<Item> getItem(Long itemId) {
        return itemJPARepository.findById(itemId);
    }
}

package com.shoplyft.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.shoplyft.backend.model.Cart;
import com.shoplyft.backend.model.Item;
import com.shoplyft.backend.model.User;
import com.shoplyft.backend.repository.CartJPARepository;
import com.shoplyft.backend.repository.ItemJPARepository;
import com.shoplyft.backend.repository.UserJPARepository;

import jakarta.transaction.Transactional;

@Service
public class CartService {

    private final CartJPARepository cartJPARepository;
    private final ItemJPARepository itemJPARepository;
    private final UserJPARepository userJPARepository;

    public CartService(CartJPARepository cartJPARepository, UserJPARepository userJPARepository, ItemJPARepository itemJPARepository) {
        this.cartJPARepository = cartJPARepository;
        this.itemJPARepository = itemJPARepository;
        this.userJPARepository = userJPARepository;
    }

    public List<Cart> findCartByUserId(Long userId) {
        User user = userJPARepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User is not found"));
        return cartJPARepository.findByUser(user);
    }

    public List<Cart> getCartByUserId(Long userId) {
        User user = userJPARepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User is not found"));
        return cartJPARepository.findByUser(user);
    }

    public void createCart(Long userId, Long itemId) {
        User user = userJPARepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("user is not found"));
        Item item = itemJPARepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("item is not found"));

        Cart cart;
        Optional<Cart> cartOptional = cartJPARepository.findByUserAndItem(user, item);
        if (cartOptional.isPresent()) {
            cart = cartOptional.get();
            cart.setQuantity(cart.getQuantity() + 1);
        } else {
            cart = new Cart(user, item);
        }
        cartJPARepository.save(cart);
    }

    public void updateCart(Long userId, Long itemId, int quantity) {
        User user = userJPARepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("user is not found"));
        Item item = itemJPARepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("item is not found"));
        Cart cart = cartJPARepository.findByUserAndItem(user, item)
                .orElseThrow(() -> new RuntimeException("cart is not found"));

        cart.setQuantity(quantity);
        cartJPARepository.save(cart);
    }

    @Transactional
    public void delete(Long userId, Long itemId) {
        cartJPARepository.deleteCartItem(userId, itemId);
    }

    public void clearCart(Long userId) {
        User user = userJPARepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("user is not found"));
        cartJPARepository.deleteByUser(user);
    }
}

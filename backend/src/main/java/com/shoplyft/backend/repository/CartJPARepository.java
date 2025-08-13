package com.shoplyft.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shoplyft.backend.model.Cart;
import com.shoplyft.backend.model.Item;
import com.shoplyft.backend.model.User;

public interface CartJPARepository extends JpaRepository<Cart, Long> {

    List<Cart> findByUser(User user);

    Optional<Cart> findByUserAndItem(User user, Item item);

    @Modifying
    @Query(value = "delete from cart_items c where c.user_id = :userId and c.item_id = :itemId", nativeQuery = true)
    void deleteCartItem(@Param("userId") Long userId, @Param("itemId") Long itemId);

    void deleteByUser(User user);
}

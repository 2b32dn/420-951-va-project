package com.shoplyft.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shoplyft.backend.model.User;

public interface UserJPARepository extends JpaRepository<User, Long> {

    User findByUserName(String username);

    @Query("SELECT u FROM User u WHERE u.id = :userId")
    User findByUserId(@Param("userId") Long userId);

    @Modifying
    @Query("UPDATE User u SET u.firstName = :firstName, u.lastName = :lastName, u.email = :email WHERE u.id = :userId")
    void updateUserDetails(@Param("userId") Long userId,
            @Param("firstName") String firstName,
            @Param("lastName") String lastName,
            @Param("email") String email);

    @Modifying
    @Query("delete from User u where u.email = :email")
    void deleteByEmail(@Param("email") String email);

    @Modifying
    @Query(value = "delete from users u where u.email = :email", nativeQuery = true)
    void deleteByEmailNative(@Param("email") String email);
}

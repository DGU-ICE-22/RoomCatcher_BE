package com.roomcatcher.RoomCatcher.repository;

import com.roomcatcher.RoomCatcher.domain.UserProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserProductRepository extends JpaRepository<UserProduct, Long> {
    Optional<UserProduct> findByUserIdAndProductId(Long userId, Long productId);
}

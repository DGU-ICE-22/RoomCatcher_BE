package com.roomcatcher.RoomCatcher.repository;

import com.roomcatcher.RoomCatcher.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Override
    Optional<Product> findById(Long id);
}
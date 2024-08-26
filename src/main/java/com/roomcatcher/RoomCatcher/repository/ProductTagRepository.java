package com.roomcatcher.RoomCatcher.repository;

import com.roomcatcher.RoomCatcher.domain.ProductTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductTagRepository extends JpaRepository<ProductTag, Long> {

    List<ProductTag> findByTagIdIn(List<Long> tagIds);

    List<ProductTag> findAllByProductId(Long productId);
}

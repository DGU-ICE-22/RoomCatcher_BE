package com.roomcatcher.RoomCatcher.repository;

import com.roomcatcher.RoomCatcher.domain.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TypeRepository extends JpaRepository<Type, Long> {

    Optional<Type> findByTypeName(String typeName);
}

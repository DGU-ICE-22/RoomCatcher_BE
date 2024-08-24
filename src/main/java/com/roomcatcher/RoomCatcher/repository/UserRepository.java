package com.roomcatcher.RoomCatcher.repository;

import com.roomcatcher.RoomCatcher.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(Long id);
}

package com.roomcatcher.RoomCatcher.repository;

import com.roomcatcher.RoomCatcher.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

package com.roomcatcher.RoomCatcher.repository;

import com.roomcatcher.RoomCatcher.domain.User;
import com.roomcatcher.RoomCatcher.domain.UserTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserTagRepository extends JpaRepository<UserTag, Long> {
    List<UserTag> findByUser(User user);
    void deleteByUser(User user);
}

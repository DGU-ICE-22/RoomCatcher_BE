package com.roomcatcher.RoomCatcher.repository;

import com.roomcatcher.RoomCatcher.domain.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTypeRepository extends JpaRepository<UserType, Long> {

    UserType findByUsersId(Long id);
}

package com.roomcatcher.RoomCatcher.repository;

import com.roomcatcher.RoomCatcher.domain.User;
import com.roomcatcher.RoomCatcher.global.exception.BusinessException;
import com.roomcatcher.RoomCatcher.global.exception.message.ErrorMessage;
import com.roomcatcher.RoomCatcher.global.jwt.JwtTokenProvider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // 이메일로 사용자 조회
    Optional<User> findByEmail(String email);

    default User findByEmailOrElseThrow(String email) {
        return findByEmail(email)
                .orElseThrow(() -> new BusinessException(ErrorMessage.NOT_FOUND_USER));
    }

    default User findByToken(String token, JwtTokenProvider jwtTokenProvider) {
        return findById(jwtTokenProvider.getUserFromJwt(token))
                   .orElseThrow(() -> new BusinessException(ErrorMessage.NOT_FOUND_USER)); // userId로 User 객체 조회
    }
}

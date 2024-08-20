package com.roomcatcher.RoomCatcher.service;

import com.roomcatcher.RoomCatcher.domain.User;
import com.roomcatcher.RoomCatcher.dto.request.UserCreateRequest;
import com.roomcatcher.RoomCatcher.dto.response.UserCreateResponse;
import com.roomcatcher.RoomCatcher.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j // 다양한 로깅 프레임워크(예: Logback, Log4j, JUL)를 사용할 수 있게하는 어노테이션
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Transactional
    public UserCreateResponse signUp(UserCreateRequest userCreateRequest) {

        User user = User.builder()
                        .userName(userCreateRequest.getName())
                        .userBirth(userCreateRequest.getBirth())
                        .email(userCreateRequest.getEmail())
                        .password(passwordEncoder.encode(userCreateRequest.getPassword())) // 비밀번호를 암호화하여 저장
                        .userSex(null)
                        .userImage(null)
                        .userType(null)
                        .build();

        return UserCreateResponse.of(userRepository.save(user).getId());
    }

}


package com.roomcatcher.RoomCatcher.service;

import com.roomcatcher.RoomCatcher.domain.User;
import com.roomcatcher.RoomCatcher.dto.request.UserCreateRequest;
import com.roomcatcher.RoomCatcher.dto.response.UserCreateResponse;
import com.roomcatcher.RoomCatcher.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    @Transactional
    public UserCreateResponse signUp(UserCreateRequest userCreateRequest) {

        User user = User.builder()
                        .userName(userCreateRequest.getName())
                        .userBirth(userCreateRequest.getBirth())
                        .email(userCreateRequest.getEmail())
                        .password(userCreateRequest.getPassword())
                        .userSex(null)
                        .userImage(null)
                        .userType(null)
                        .build();

        return UserCreateResponse.of(userRepository.save(user).getId());
    }

}


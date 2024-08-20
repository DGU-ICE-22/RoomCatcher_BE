package com.roomcatcher.RoomCatcher.service;

import com.roomcatcher.RoomCatcher.domain.User;
import com.roomcatcher.RoomCatcher.dto.request.UserCreateRequest;
import com.roomcatcher.RoomCatcher.dto.request.UserLoginRequest;
import com.roomcatcher.RoomCatcher.dto.response.UserCreateResponse;
import com.roomcatcher.RoomCatcher.dto.response.UserLoginResponse;
import com.roomcatcher.RoomCatcher.global.exception.BusinessException;
import com.roomcatcher.RoomCatcher.global.exception.message.ErrorMessage;
import com.roomcatcher.RoomCatcher.global.jwt.JwtTokenProvider;
import com.roomcatcher.RoomCatcher.global.jwt.UserAuthentication;
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
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public UserCreateResponse signUp(UserCreateRequest userCreateRequest) {

        // 이메일 중복 체크
        if (userRepository.findByEmail(userCreateRequest.getEmail()).isPresent()) {
            throw new BusinessException(ErrorMessage.DUPLICATE_EMAIL);
        }

        // 유저 생성
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

    @Transactional(readOnly = true)
    public UserLoginResponse signIn(UserLoginRequest userLoginRequest) {
        // 이메일로 멤버 찾기
        User user = userRepository.findByEmailOrElseThrow(userLoginRequest.getEmail());

        // 비밀번호 검증
        if (!passwordEncoder.matches(userLoginRequest.getPassword(), user.getPassword())) {
            throw new BusinessException(ErrorMessage.INVALID_PASSWORD);
        }

        /// AccessToken 발행
        String accessToken = jwtTokenProvider.issueAccessToken(
            UserAuthentication.createUserAuthentication(user.getId())
        );

        return UserLoginResponse.of(accessToken, String.valueOf(user.getId()));
    }
}


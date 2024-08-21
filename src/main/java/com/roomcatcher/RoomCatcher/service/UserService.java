package com.roomcatcher.RoomCatcher.service;

import com.roomcatcher.RoomCatcher.domain.User;
import com.roomcatcher.RoomCatcher.dto.request.UserCreateRequest;
import com.roomcatcher.RoomCatcher.dto.request.UserLoginRequest;
import com.roomcatcher.RoomCatcher.dto.response.UserLoginResponse;
import com.roomcatcher.RoomCatcher.dto.response.UserResponse;
import com.roomcatcher.RoomCatcher.global.exception.BusinessException;
import com.roomcatcher.RoomCatcher.global.exception.message.ErrorMessage;
import com.roomcatcher.RoomCatcher.global.jwt.JwtTokenProvider;
import com.roomcatcher.RoomCatcher.global.jwt.JwtValidationType;
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
    public UserResponse signUp(UserCreateRequest userCreateRequest) {

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

        return UserResponse.of(userRepository.save(user).getId());
    }

    @Transactional
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

        // 현재 토큰을 db에 저장
        user.setCurrentToken(accessToken);
        userRepository.save(user);

        return UserLoginResponse.of(accessToken, String.valueOf(user.getId()));
    }

    @Transactional
    public UserResponse logout(String token) {

        User user = validateTokenAndGetUser(token);

        // 현재 가지고 있는 토큰과 요청으로 들어온 토큰이 일치하는지 확인
        if (!token.equals(user.getCurrentToken())) {
            throw new BusinessException(ErrorMessage.INVALID_JWT_TOKEN);
        }

        // 현재 가지고 있는 유효한 토큰을 무효화
        user.setCurrentToken(null);
        userRepository.save(user);

        return UserResponse.of(user.getId());
    }

    // 토큰이 유효한지와 사용자 정보를 추출하는 메서드
    private User validateTokenAndGetUser(String token) {
        if (jwtTokenProvider.validateToken(token) != JwtValidationType.VALID_JWT) {
            throw new BusinessException(ErrorMessage.INVALID_JWT_TOKEN);
        }

        // 토큰에서 사용자 정보 추출
        Long userId = jwtTokenProvider.getUserFromJwt(token);
        return userRepository.findById(userId)
                   .orElseThrow(() -> new BusinessException(ErrorMessage.NOT_FOUND_USER));
    }
}


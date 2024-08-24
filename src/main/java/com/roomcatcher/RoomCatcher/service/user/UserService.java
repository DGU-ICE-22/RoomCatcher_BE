package com.roomcatcher.RoomCatcher.service.user;

import com.roomcatcher.RoomCatcher.domain.User;
import com.roomcatcher.RoomCatcher.dto.user.request.UserCreateRequest;
import com.roomcatcher.RoomCatcher.dto.user.request.UserLoginRequest;
import com.roomcatcher.RoomCatcher.dto.user.response.UserLoginResponse;
import com.roomcatcher.RoomCatcher.dto.user.response.UserResponse;
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

import java.util.HashSet;
import java.util.Set;

@Slf4j // 다양한 로깅 프레임워크(예: Logback, Log4j, JUL)를 사용할 수 있게하는 어노테이션
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    // 로그아웃된 토큰을 저장하는 블랙리스트
    private final Set<String> tokenBlacklist = new HashSet<>();

    @Transactional
    public UserResponse signUp(UserCreateRequest userCreateRequest) {

        // 이메일 중복 체크
        if (userRepository.findByEmail(userCreateRequest.getEmail()).isPresent()) {
            throw new BusinessException(ErrorMessage.DUPLICATE_EMAIL);
        }

        // 홀수면 남자, 짝수면 여자
        String gender = (userCreateRequest.getSex() % 2 == 0) ? "여자" : "남자";

        // 유저 생성
        User user = User.builder()
                        .userName(userCreateRequest.getName())
                        .userBirth(userCreateRequest.getBirth())
                        .email(userCreateRequest.getEmail())
                        .password(passwordEncoder.encode(userCreateRequest.getPassword())) // 비밀번호를 암호화하여 저장
                        .userSex(gender)
                        .userImage(null)
                        .userType(null)
                        .userlocation(null)
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

        return UserLoginResponse.of(accessToken, String.valueOf(user.getId()));
    }

    @Transactional
    public UserResponse logout(String token) {
        // 이미 로그아웃된 토큰인지 확인
        if (tokenBlacklist.contains(token)) {
            throw new BusinessException(ErrorMessage.ALREADY_LOGOUT_TOKEN);
        }

        // 토큰 유효성 검사
        if (jwtTokenProvider.validateToken(token) != JwtValidationType.VALID_JWT) {
            throw new BusinessException(ErrorMessage.INVALID_JWT_TOKEN);
        }

        // 사용자 존재 여부 확인 -> 즉 이미 회원탈퇴를 했는지
        User user = userRepository.findById(jwtTokenProvider.getUserFromJwt(token)).orElseThrow(() -> {
            throw new BusinessException(ErrorMessage.DEACTIVATED_ACCOUNT);
        });

        // 블랙리스트에 토큰 추가
        tokenBlacklist.add(token);

        return UserResponse.of(user.getId());
    }

    @Transactional
    public void deleteAccount(String token) {
        // 블랙리스트에 있는 토큰인지 확인
        if (tokenBlacklist.contains(token)) {
            throw new BusinessException(ErrorMessage.INVALID_JWT_TOKEN); // 이미 로그아웃된 토큰이므로 탈퇴를 진행하지 않음
        }

        // 토큰 유효성 검사
        if (jwtTokenProvider.validateToken(token) != JwtValidationType.VALID_JWT) {
            throw new BusinessException(ErrorMessage.INVALID_JWT_TOKEN);
        }

        // 사용자 정보 확인 및 계정 삭제
        User user = userRepository.findById(jwtTokenProvider.getUserFromJwt(token))
                        .orElseThrow(() -> new BusinessException(ErrorMessage.NOT_FOUND_USER));

        // 사용자 삭제
        userRepository.delete(user);
    }
}


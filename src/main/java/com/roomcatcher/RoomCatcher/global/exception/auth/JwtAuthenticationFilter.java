package com.roomcatcher.RoomCatcher.global.exception.auth;

import com.roomcatcher.RoomCatcher.global.exception.UnauthorizedException;
import com.roomcatcher.RoomCatcher.global.exception.message.ErrorMessage;
import com.roomcatcher.RoomCatcher.global.jwt.JwtTokenProvider;
import com.roomcatcher.RoomCatcher.global.jwt.UserAuthentication;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static com.roomcatcher.RoomCatcher.global.jwt.JwtValidationType.VALID_JWT;

// 요청에서 Jwt를 검증하는 커스텀 필터 클래스
@Component // 필터를 빈으로 등록
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter  { // 요청이 주어졌을때 한번만 수행되는 필터를 상속받음

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        try {
            final String token = getJwtFromRequest(request);
            if (jwtTokenProvider.validateToken(token) == VALID_JWT) { // 추출한 토큰의 정보가 VALID_JWT일 경우 사용자 정보 추출
                Long memberId = jwtTokenProvider.getUserFromJwt(token);

                UserAuthentication authentication = UserAuthentication.createUserAuthentication(memberId);
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // 추출한 UserId 기반 authentication 객체 생성

                SecurityContextHolder.getContext().setAuthentication(authentication); // SecurityContextHolder에 User 정보 저장
            }
        } catch (Exception exception) {
            throw new UnauthorizedException(ErrorMessage.JWT_UNAUTHORIZED_EXCEPTION); // 토큰 추출 과정에서 에러 발생 시 UnauthorizedException 발생
        }
        filterChain.doFilter(request, response);
    }

    // Request에서 Token을 추출하는 메서드
    private String getJwtFromRequest(HttpServletRequest request) {
        String token = request.getHeader("Authorization"); // Request의 Authorization 헤더값 추출
        if (StringUtils.hasText(token)) {
            return token; // Bearer 없이 AccessToken을 반환
        }
        return null;
    }
}
package com.roomcatcher.RoomCatcher.global.exception.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.RequestCacheConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CustomJwtAuthenticationEntryPoint customJwtAuthenticationEntryPoint;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    private static final String[] AUTH_WHITE_LIST = {"/api/register", "/api/login", "api/mypage/tags" }; // 화이트 리스트 경로를 추가하여 이 경로는 인증 없이 접근 가능

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .formLogin(AbstractHttpConfigurer::disable)
            .requestCache(RequestCacheConfigurer::disable)
            .httpBasic(AbstractHttpConfigurer::disable)
            .exceptionHandling(exception -> {
                exception.authenticationEntryPoint(customJwtAuthenticationEntryPoint);
                exception.accessDeniedHandler(customAccessDeniedHandler);
                // 예외처리 -> 만들었던 handler 2개 등록
                // 인증 실패 시 CustomJwtAuthenticationEntryPoint를 통해 처리
                // 권한(인가) 부족 시 CustomAccessDeniedHandler가 처리
            })
            .authorizeHttpRequests(auth -> {
                auth.requestMatchers(AUTH_WHITE_LIST).permitAll(); // 화이트리스트 경로 허용
                auth.anyRequest().authenticated(); // 나머지 요청은 인증 필요
                // 즉, 유저 회원 가입이나 로그인 등 인증 전 단계의 API를 WHITE_LIST에 등록한 후 그 요청의 경우 허용, 나머지는 인증을 진행
            })
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
            // 이렇게 필터를 추가하면 이미 SecurityContextHolder에 저장된 유저 정보로 이후 인증 필터에서 인증과정은 통과

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // 비밀번호 암호화를 위한 BCryptPasswordEncoder 빈 등록
    }
}

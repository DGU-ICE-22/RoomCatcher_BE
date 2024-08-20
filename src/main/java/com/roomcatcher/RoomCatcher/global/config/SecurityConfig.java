package com.roomcatcher.RoomCatcher.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())  // CSRF 보호 비활성화
            .authorizeHttpRequests(auth -> auth
                                               .requestMatchers("/api/register").permitAll()
                                               .anyRequest().authenticated()
            )
            .formLogin(formLogin -> formLogin.disable());   // 기본 로그인 폼 비활성화
        return http.build();
    }
}

package com.roomcatcher.RoomCatcher.global.jwt;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import javax.swing.*;
import java.util.Collection;

public class UserAuthentication extends UsernamePasswordAuthenticationToken {
    // UsernamePasswordAuthenticationToken은 Authentication의 구현체로 Spring Security에서 Username/Password로 인증을 수행하기 위해 필요한 토큰

    // UsernamePasswordAuthenticationToken은 식별자를 담을 수 있는 객체가 아니기 때문에 상속받아 Principle에 식별자를 담아줌
    public UserAuthentication(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }

    public static UserAuthentication createUserAuthentication(Long userId) {
        return new UserAuthentication(userId, null, null);
    }
}
package com.roomcatcher.RoomCatcher.global.exception.auth;

import com.roomcatcher.RoomCatcher.global.exception.UnauthorizedException;
import com.roomcatcher.RoomCatcher.global.exception.message.ErrorMessage;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

//  member의 식별자를 가져오기 위한 클래스
@Component
public class PrincipalHandler {

    private static final String ANONYMOUS_USER = "anonymousUser";

    public Long getUserIdFromPrincipal() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // SecurityContextHolder에 저장되어 있는 Authetication객체를 추출하여 식별자를 꺼냄
        isPrincipalNull(principal);
        return Long.valueOf(principal.toString());
    }

    public void isPrincipalNull(
        final Object principal
    ) {
        if (principal.toString().equals(ANONYMOUS_USER)) {
            throw new UnauthorizedException(ErrorMessage.JWT_UNAUTHORIZED_EXCEPTION);
        }
        // 만일 Principal에 아무것도 담겨있지 않으면 AnonymousUser를 리턴함 -> 이럴 경우 예외를 발생
    }
}
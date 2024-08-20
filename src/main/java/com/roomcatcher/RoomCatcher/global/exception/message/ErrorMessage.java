package com.roomcatcher.RoomCatcher.global.exception.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorMessage {

    NOT_FOUND_USER(HttpStatus.NO_CONTENT.value(), "이메일에 해당하는 사용자가 없습니다."),
    DUPLICATE_EMAIL(HttpStatus.CONFLICT.value(), "이미 가입한 사용자 입니다."),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST.value(), "비밀번호가 일치하지 않습니다."),
    JWT_UNAUTHORIZED_EXCEPTION(HttpStatus.UNAUTHORIZED.value(), "사용자의 로그인 검증을 실패했습니다.")
    ;

    private final int code;
    private final String message;
}

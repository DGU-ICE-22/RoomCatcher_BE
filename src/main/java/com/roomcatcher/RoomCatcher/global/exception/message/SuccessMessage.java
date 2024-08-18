package com.roomcatcher.RoomCatcher.global.exception.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessMessage {


    SIGNUP_SUCCESS(HttpStatus.CREATED.value(),"회원가입에 성공하였습니다.")
    ;
    private final int code;
    private final String message;
}
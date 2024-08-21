package com.roomcatcher.RoomCatcher.global.exception.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessMessage {


    SIGNUP_SUCCESS(HttpStatus.CREATED.value(),"회원가입에 성공하였습니다."),
    SIGNIN_SUCCESS(HttpStatus.CREATED.value(),"로그인에 성공하였습니다."),
    LOGOUT_SUCCESS(HttpStatus.OK.value(),"로그아웃에 성공하였습니다."),
    ACCOUNT_DELETION_SUCCESS(HttpStatus.OK.value(),"회원 탈퇴에 성공하였습니다."),
    ;
    private final int code;
    private final String message;
}
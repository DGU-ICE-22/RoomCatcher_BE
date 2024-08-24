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
    TAGS_GET_SUCCESS(HttpStatus.OK.value(),"해시태그 목록 조회에 성공하였습니다."),
    TAGS_UPDATE_SUCCESS(HttpStatus.CREATED.value(),"유저의 선호정보 업데이트에 성공하였습니다."),
    MYPAGE_GET_SUCCESS(HttpStatus.OK.value(),"마이페이지 조회에 성공하였습니다."),
    INFO_UPDATE_SUCCESS(HttpStatus.CREATED.value(),"유저의 내 정보 업데이트에 성공하였습니다.")
    ;
    private final int code;
    private final String message;
}
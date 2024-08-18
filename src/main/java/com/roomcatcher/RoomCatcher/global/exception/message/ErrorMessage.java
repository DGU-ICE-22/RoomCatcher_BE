package com.roomcatcher.RoomCatcher.global.exception.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorMessage {

    NOT_FOUND_USER(HttpStatus.NO_CONTENT.value(), "ID에 해당하는 사용자가 없습니다.");

    private final int code;
    private final String message;
}

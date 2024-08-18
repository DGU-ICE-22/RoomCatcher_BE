package com.roomcatcher.RoomCatcher.global.exception;

import com.roomcatcher.RoomCatcher.global.exception.message.ErrorMessage;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    private ErrorMessage errorMessage;
    public BusinessException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
        this.errorMessage = errorMessage;
    }
}
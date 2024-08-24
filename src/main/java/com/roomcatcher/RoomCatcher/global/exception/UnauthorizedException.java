package com.roomcatcher.RoomCatcher.global.exception;

import com.roomcatcher.RoomCatcher.global.exception.message.ErrorMessage;

public class UnauthorizedException extends BusinessException {
    public UnauthorizedException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}

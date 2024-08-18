package com.roomcatcher.RoomCatcher.global.exception;

import com.roomcatcher.RoomCatcher.global.exception.message.ErrorMessage;

public class NotFoundException extends BusinessException {
    public NotFoundException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}

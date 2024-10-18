package com.roomcatcher.RoomCatcher.dto.mypage.request;

import java.util.List;

public record UserTagRequestDto(
        List<String> tagNames
) {
}

package com.roomcatcher.RoomCatcher.dto.response;

public record UserLoginResponse(
    String accessToken,
    String userId
) {

    public static UserLoginResponse of(
        String accessToken,
        String userId
    ) {
        return new UserLoginResponse(accessToken, userId);
    }
}
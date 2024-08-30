package com.roomcatcher.RoomCatcher.dto.user.response;

public record UserLoginResponse(
    String accessToken,
    String userId,
    String userName
) {

    public static UserLoginResponse of(
        String accessToken,
        String userId,
        String userName
    ) {
        return new UserLoginResponse(accessToken, userId, userName);
    }
}
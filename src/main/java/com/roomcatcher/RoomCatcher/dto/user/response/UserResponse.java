package com.roomcatcher.RoomCatcher.dto.user.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private Long userId;

    public static UserResponse of(Long memberId) {
        return new UserResponse(memberId);
    }
}
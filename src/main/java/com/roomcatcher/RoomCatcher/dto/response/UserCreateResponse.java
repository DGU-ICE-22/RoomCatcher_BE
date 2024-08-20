package com.roomcatcher.RoomCatcher.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCreateResponse {
    private Long userId;

    public static UserCreateResponse of(Long memberId) {
        return new UserCreateResponse(memberId);
    }
}

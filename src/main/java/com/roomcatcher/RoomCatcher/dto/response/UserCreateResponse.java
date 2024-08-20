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
    private Long memberId;

    // memberId를 받아서 UserCreateResponse 객체를 반환하는 정적 메서드
    public static UserCreateResponse of(Long memberId) {
        return new UserCreateResponse(memberId);
    }
}

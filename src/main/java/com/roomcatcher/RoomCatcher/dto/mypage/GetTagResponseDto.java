package com.roomcatcher.RoomCatcher.dto.mypage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetTagResponseDto {
    private List<String> tagName;

    public static GetTagResponseDto of(List<String> tagName) {
        return new GetTagResponseDto(tagName);
    }
}
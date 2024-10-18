package com.roomcatcher.RoomCatcher.dto.mypage.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
public record GetTagResponseDto (
    List<String> tagName
){
    public static GetTagResponseDto of(List<String> tagName) {
        return new GetTagResponseDto(tagName);
    }
}
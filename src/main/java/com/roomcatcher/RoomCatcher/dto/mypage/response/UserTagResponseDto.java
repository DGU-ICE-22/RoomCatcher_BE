package com.roomcatcher.RoomCatcher.dto.mypage.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public record UserTagResponseDto (
    List<String> userTags
){
}
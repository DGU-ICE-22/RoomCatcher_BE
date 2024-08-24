package com.roomcatcher.RoomCatcher.dto.mypage.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserTagRequestDto {
    private List<String> tagNames;
}

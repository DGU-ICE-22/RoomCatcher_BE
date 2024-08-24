package com.roomcatcher.RoomCatcher.dto.mypage.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoRequestDto {

    private String birth;

    private String sex;

    private String residence;
}

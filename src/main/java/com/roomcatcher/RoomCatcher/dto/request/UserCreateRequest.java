package com.roomcatcher.RoomCatcher.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRequest{


    private String name;

    private String email;

    private String password;

    private String birth;
}

package com.roomcatcher.RoomCatcher.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginRequest {

    @NotBlank(message = "이메일을 입력해야 합니다.")
    private String email;

    @NotBlank(message = "비밀번호를 입력해야 합니다.")
    private String password;
}
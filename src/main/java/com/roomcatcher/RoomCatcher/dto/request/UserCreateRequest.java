package com.roomcatcher.RoomCatcher.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRequest {

    @NotBlank(message = "이름을 입력해야 합니다.")
    private String name;

    @NotBlank(message = "이메일을 입력해야 합니다.")
    private String email;

    @NotBlank(message = "비밀번호를 입력해야 합니다.")
    private String password;

    @NotBlank(message = "생년월일을 입력해야 합니다.")
    private String birth;

    @NotNull(message = "성별을 입력해야 합니다.")
    private Integer sex;
}

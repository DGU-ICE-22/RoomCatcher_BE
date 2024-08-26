package com.roomcatcher.RoomCatcher.dto.usertype.request;

import com.roomcatcher.RoomCatcher.domain.Tag;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateUserTypeDTO {
    private String typeName;
    private List<String> tags;
}

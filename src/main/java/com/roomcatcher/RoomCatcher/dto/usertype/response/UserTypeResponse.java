package com.roomcatcher.RoomCatcher.dto.usertype.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserTypeResponse {
    private Long userTypeId;

   public static UserTypeResponse of(Long typeId) {
      return new UserTypeResponse(typeId);
   }
}


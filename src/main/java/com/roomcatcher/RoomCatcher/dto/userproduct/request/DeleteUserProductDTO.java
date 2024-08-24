package com.roomcatcher.RoomCatcher.dto.userproduct.request;

import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class DeleteUserProductDTO {
    private Long userId;
    private Long productId;
}

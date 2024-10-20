package com.roomcatcher.RoomCatcher.dto.mypage.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
public record UserProductResponseDto (
    Long id,
    String productName,
    String address,
    String typeOfProperty,
    Integer monthlyRent,
    List<String> tags
){
}
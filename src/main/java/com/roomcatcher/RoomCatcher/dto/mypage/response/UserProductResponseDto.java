package com.roomcatcher.RoomCatcher.dto.mypage.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserProductResponseDto {
    private Long id;
    private String productName;
    private String address;
    private String typeOfProperty;
    private Integer monthlyRent;
    private List<String> tags;
}
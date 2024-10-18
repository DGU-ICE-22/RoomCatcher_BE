package com.roomcatcher.RoomCatcher.dto.mypage.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
public record MypageReponseDto (

    String name,

    String birth,

    String sex,

    String residence,

    List<String> myTags
){
    public static MypageReponseDto of(String name, String birth, String sex, String residence, List<String> myTags) {
        return MypageReponseDto.builder()
                   .name(name)
                   .birth(birth)
                   .sex(sex)
                   .residence(residence)
                   .myTags(myTags)
                   .build();
    }
}
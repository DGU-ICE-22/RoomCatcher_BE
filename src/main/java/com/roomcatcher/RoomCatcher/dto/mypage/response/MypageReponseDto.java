package com.roomcatcher.RoomCatcher.dto.mypage.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MypageReponseDto {

    private String name;

    private String birth;

    private String sex;

    private String residence;

    private List<String> myTags;

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
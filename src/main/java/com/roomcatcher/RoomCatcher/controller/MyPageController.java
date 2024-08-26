package com.roomcatcher.RoomCatcher.controller;

import com.roomcatcher.RoomCatcher.dto.mypage.request.UserInfoRequestDto;
import com.roomcatcher.RoomCatcher.dto.mypage.request.UserTagRequestDto;
import com.roomcatcher.RoomCatcher.dto.mypage.response.GetTagResponseDto;
import com.roomcatcher.RoomCatcher.dto.mypage.response.MypageReponseDto;
import com.roomcatcher.RoomCatcher.dto.mypage.response.UserProductResponseDto;
import com.roomcatcher.RoomCatcher.dto.mypage.response.UserTagResponseDto;
import com.roomcatcher.RoomCatcher.global.exception.dto.SuccessStatusResponse;
import com.roomcatcher.RoomCatcher.global.exception.message.SuccessMessage;
import com.roomcatcher.RoomCatcher.service.mypage.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mypage")
@RequiredArgsConstructor
public class MyPageController {

    private final MyPageService myPageService;

    // 해시태그 목록 조회
    @GetMapping("/tags")
    public ResponseEntity<SuccessStatusResponse<GetTagResponseDto>> getTags() {
        return ResponseEntity.status(HttpStatus.OK).body(SuccessStatusResponse.of(SuccessMessage.TAGS_GET_SUCCESS, myPageService.getTags()));
    }

    // 사용자 선호정보(해시태그) 목록 업데이트
    @PostMapping("/userTags")
    public ResponseEntity<SuccessStatusResponse<UserTagResponseDto>> updateUserTags(@RequestHeader("Authorization") String token, @RequestBody UserTagRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(SuccessStatusResponse.of(SuccessMessage.TAGS_UPDATE_SUCCESS, myPageService.updateUserTags(token, requestDto)));
    }

    // 마이페이지 조회
    @GetMapping
    public ResponseEntity<SuccessStatusResponse<MypageReponseDto>> getMyPage(@RequestHeader("Authorization") String token) {
        return ResponseEntity.status(HttpStatus.OK).body(SuccessStatusResponse.of(SuccessMessage.MYPAGE_GET_SUCCESS, myPageService.getMyPage(token)));
    }

    // 마이페이지 정보 수정
    @PatchMapping("/userInfo")
    public ResponseEntity<SuccessStatusResponse<Void>> FixMyPage(@RequestHeader("Authorization") String token, @RequestBody UserInfoRequestDto userInfoRequestDto) {
        myPageService.fixMyPage(token, userInfoRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(SuccessStatusResponse.of(SuccessMessage.INFO_UPDATE_SUCCESS));
    }

    @GetMapping("/userProducts")
    public ResponseEntity<SuccessStatusResponse<List<UserProductResponseDto>>> getLikedProducts(@RequestHeader("Authorization") String token) {
        return ResponseEntity.status(HttpStatus.OK).body(SuccessStatusResponse.of(SuccessMessage.LIKED_PRODUCT_GET_SUCCESS, myPageService.getLikedProducts(token)));
    }
}

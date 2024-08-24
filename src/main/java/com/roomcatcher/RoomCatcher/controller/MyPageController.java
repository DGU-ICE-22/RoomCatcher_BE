package com.roomcatcher.RoomCatcher.controller;

import com.roomcatcher.RoomCatcher.dto.mypage.GetTagResponseDto;
import com.roomcatcher.RoomCatcher.global.exception.dto.SuccessStatusResponse;
import com.roomcatcher.RoomCatcher.global.exception.message.SuccessMessage;
import com.roomcatcher.RoomCatcher.service.mypage.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}

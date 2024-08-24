package com.roomcatcher.RoomCatcher.controller;

import com.roomcatcher.RoomCatcher.dto.user.request.UserCreateRequest;
import com.roomcatcher.RoomCatcher.dto.user.request.UserLoginRequest;
import com.roomcatcher.RoomCatcher.dto.user.response.UserLoginResponse;
import com.roomcatcher.RoomCatcher.dto.user.response.UserResponse;
import com.roomcatcher.RoomCatcher.global.exception.dto.SuccessStatusResponse;
import com.roomcatcher.RoomCatcher.global.exception.message.SuccessMessage;
import com.roomcatcher.RoomCatcher.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 회원가입
    @PostMapping("/register")
    public ResponseEntity<SuccessStatusResponse<UserResponse>> signUp(@Valid @RequestBody UserCreateRequest userCreateRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(SuccessStatusResponse.of(SuccessMessage.SIGNUP_SUCCESS, userService.signUp(userCreateRequest)));
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<SuccessStatusResponse<UserLoginResponse>> signIn(@Valid @RequestBody UserLoginRequest userLoginRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(SuccessStatusResponse.of(SuccessMessage.SIGNIN_SUCCESS, userService.signIn(userLoginRequest)));
    }

    // 로그아웃
    @PostMapping("/logout")
    public ResponseEntity<SuccessStatusResponse<UserResponse>> logout(@RequestHeader("Authorization") String token) {
        return ResponseEntity.status(HttpStatus.OK).body(SuccessStatusResponse.of(SuccessMessage.LOGOUT_SUCCESS, userService.logout(token)));
    }

    // 회원탈퇴
    @DeleteMapping("/users/delete")
    public ResponseEntity<SuccessStatusResponse<Void>> deleteAccount(@RequestHeader("Authorization") String token) {
        userService.deleteAccount(token);
        return ResponseEntity.status(HttpStatus.OK).body(SuccessStatusResponse.of(SuccessMessage.ACCOUNT_DELETION_SUCCESS, null));
    }
}

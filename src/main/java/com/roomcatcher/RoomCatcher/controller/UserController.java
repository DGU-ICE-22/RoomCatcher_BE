package com.roomcatcher.RoomCatcher.controller;

import com.roomcatcher.RoomCatcher.dto.request.UserCreateRequest;
import com.roomcatcher.RoomCatcher.dto.request.UserLoginRequest;
import com.roomcatcher.RoomCatcher.dto.response.UserCreateResponse;
import com.roomcatcher.RoomCatcher.dto.response.UserLoginResponse;
import com.roomcatcher.RoomCatcher.global.exception.auth.PrincipalHandler;
import com.roomcatcher.RoomCatcher.global.exception.dto.SuccessStatusResponse;
import com.roomcatcher.RoomCatcher.global.exception.message.SuccessMessage;
import com.roomcatcher.RoomCatcher.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 회원가입
    @PostMapping("/register")
    public ResponseEntity<SuccessStatusResponse<UserCreateResponse>> signUp(@Valid @RequestBody UserCreateRequest userCreateRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(SuccessStatusResponse.of(SuccessMessage.SIGNUP_SUCCESS, userService.signUp(userCreateRequest)));
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<SuccessStatusResponse<UserLoginResponse>> signIn(@Valid @RequestBody UserLoginRequest userLoginRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(SuccessStatusResponse.of(SuccessMessage.SIGNIN_SUCCESS, userService.signIn(userLoginRequest)));
    }
}

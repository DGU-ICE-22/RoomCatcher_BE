package com.roomcatcher.RoomCatcher.controller;

import com.roomcatcher.RoomCatcher.dto.usertype.request.CreateUserTypeDTO;
import com.roomcatcher.RoomCatcher.global.exception.dto.SuccessStatusResponse;
import com.roomcatcher.RoomCatcher.global.exception.message.SuccessMessage;
import com.roomcatcher.RoomCatcher.service.usertype.UserTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/report")
public class UserTypeController {
    private final UserTypeService userTypeService;
    @PostMapping
    public ResponseEntity<?> createUserType(@RequestHeader("Authorization") String token, @RequestBody CreateUserTypeDTO createUserTypeDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(SuccessStatusResponse.of(SuccessMessage.CREATE_USER_TYPE, userTypeService.createUserType(token, createUserTypeDTO)));
    }
}

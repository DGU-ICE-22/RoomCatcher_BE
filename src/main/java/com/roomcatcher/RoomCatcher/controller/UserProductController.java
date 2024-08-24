package com.roomcatcher.RoomCatcher.controller;

import com.roomcatcher.RoomCatcher.service.userproduct.UserProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/like")
@Slf4j
public class UserProductController {
    private final UserProductService userProductService;

    @PostMapping
    public void createUserProduct(@RequestHeader ("Authorization") String token, @RequestParam Long productId) {
        userProductService.createUserProduct(token, productId);
    }

    @DeleteMapping
    public void deleteUserProduct(@RequestHeader ("Authorization") String token, @RequestParam Long productId) {
        userProductService.deleteUserProduct(token, productId);
    }
}

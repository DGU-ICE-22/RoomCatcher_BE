package com.roomcatcher.RoomCatcher.controller;

import com.roomcatcher.RoomCatcher.dto.userproduct.request.CreateUserProductDTO;
import com.roomcatcher.RoomCatcher.service.userproduct.UserProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/like")
public class UserProductController {
    private final UserProductService userProductService;

    @PostMapping
    public void createUserProduct(@RequestBody CreateUserProductDTO createProductDTO) {
        userProductService.createUserProduct(createProductDTO);
    }

    @DeleteMapping
    public void deleteUserProduct(@RequestParam Long userId, @RequestParam Long productId) {
        userProductService.deleteUserProduct(userId, productId);
    }
}

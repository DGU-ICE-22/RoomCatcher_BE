package com.roomcatcher.RoomCatcher.controller;

import com.roomcatcher.RoomCatcher.dto.userproduct.request.CreateUserProductDTO;
import com.roomcatcher.RoomCatcher.dto.userproduct.request.DeleteUserProductDTO;
import com.roomcatcher.RoomCatcher.service.userproduct.UserProductService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/like")
public class UserProductController {
    private static final Logger log = LoggerFactory.getLogger(UserProductController.class);
    private final UserProductService userProductService;

    @PostMapping
    public void createUserProduct(@RequestBody CreateUserProductDTO createProductDTO) {
        log.info("createUserProduct");
        userProductService.createUserProduct(createProductDTO);
    }

    @DeleteMapping
    public void deleteUserProduct(@RequestBody DeleteUserProductDTO deleteUserProductDTO) {
        userProductService.deleteUserProduct(deleteUserProductDTO);
    }
}

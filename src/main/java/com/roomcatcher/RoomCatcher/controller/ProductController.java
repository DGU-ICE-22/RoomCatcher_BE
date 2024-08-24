package com.roomcatcher.RoomCatcher.controller;

import com.roomcatcher.RoomCatcher.domain.Product;
import com.roomcatcher.RoomCatcher.dto.product.request.ProductRequestDTO;
import com.roomcatcher.RoomCatcher.dto.product.response.ProductListResponseDTO;
import com.roomcatcher.RoomCatcher.dto.product.response.ProductResponseDTO;
import com.roomcatcher.RoomCatcher.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/recommend")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<?> getProduct(@RequestHeader ("Authorization") String token) {
        //RequestParam 말고 토큰 사용해서 userId 가져오기로 바꾸기
        return ResponseEntity.ok(productService.getProduct(token));
    }
}

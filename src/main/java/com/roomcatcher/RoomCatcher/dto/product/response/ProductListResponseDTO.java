package com.roomcatcher.RoomCatcher.dto.product.response;

import lombok.Builder;

import java.util.List;

@Builder
public record ProductListResponseDTO(
        List<ProductResponseDTO> productList
) {
    public static ProductListResponseDTO of(List<ProductResponseDTO> productList) {
        return ProductListResponseDTO.builder()
                .productList(productList)
                .build();
    }
}

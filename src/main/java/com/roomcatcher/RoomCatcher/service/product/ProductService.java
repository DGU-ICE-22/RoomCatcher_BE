package com.roomcatcher.RoomCatcher.service.product;

import com.roomcatcher.RoomCatcher.domain.Product;
import com.roomcatcher.RoomCatcher.domain.ProductTag;
import com.roomcatcher.RoomCatcher.domain.Tag;
import com.roomcatcher.RoomCatcher.domain.User;
import com.roomcatcher.RoomCatcher.global.jwt.JwtTokenProvider;
import com.roomcatcher.RoomCatcher.dto.product.request.ProductRequestDTO;
import com.roomcatcher.RoomCatcher.dto.product.response.ProductListResponseDTO;
import com.roomcatcher.RoomCatcher.dto.product.response.ProductResponseDTO;
import com.roomcatcher.RoomCatcher.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {
    private final TagRepository tagRepository;
    private final ProductTagRepository productTagRepository;
    private final ProductRepository productRepository;
    private final UserTagRepository userTagRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public ProductListResponseDTO getProduct(String token) {
        try {
            //토큰으로 userId를 가져옴.
            Long userId = jwtTokenProvider.getUserFromJwt(token);
            // userId로 userTag를 가져옴.
            List<Long> tagIds = userTagRepository.findByUserId(userId)
                    .stream()
                    .map(userTag -> userTag.getTag().getId())
                    .toList();
            log.info("tagIds: {}", tagIds);

// 그 tagId로 productTagRepository에서 productTag를 가져옴.
            List<ProductTag> productTags = productTagRepository.findByTagIdIn(tagIds);

// productId별로 매칭되는 tagId 개수를 계산함.
            Map<Long, Long> productTagCountMap = productTags.stream()
                    .collect(Collectors.groupingBy(
                            productTag -> productTag.getProduct().getId(),
                            Collectors.counting()
                    ));

// 겹치는 태그 개수가 많은 순서대로 productId를 리스트화
            List<Long> sortedProductIds = productTagCountMap.entrySet().stream()
                    .filter(entry -> entry.getValue() >= 5)  // 2개 이상의 태그가 일치하는 것만 필터링
                    .sorted((entry1, entry2) -> Long.compare(entry2.getValue(), entry1.getValue()))  // 개수 기준으로 내림차순 정렬
                    .map(Map.Entry::getKey)
                    .toList();
            List<Long> limitedProductIds = sortedProductIds.size() > 10 ? sortedProductIds.subList(0, 10) : sortedProductIds;

            log.info("Sorted productIds by matching tag count: {}", limitedProductIds);
            List<Product> productList = productRepository.findByIdIn(limitedProductIds);

            List<ProductResponseDTO> productResponseDTOList = productList.stream()
                    .map(ProductResponseDTO::of)
                    .toList();

            return ProductListResponseDTO.of(productResponseDTOList);
        }
        catch (Exception e) {
            System.out.println("Error: " + e);
            return null;
        }
    }
}

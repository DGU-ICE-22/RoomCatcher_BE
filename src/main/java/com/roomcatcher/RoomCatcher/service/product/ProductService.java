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
            //userId로 userTag를 가져옴.
            List<Long> tagIds = userTagRepository.findByUserId(userId)
                    .stream()
                    .map(userTag -> userTag.getTag().getId())
                    .toList();
            log.info("tagIds: {}", tagIds);
            //그 tagId로 productTagRepository에서 productTag를 가져옴.
            List<ProductTag> productTags = productTagRepository.findByTagIdIn(tagIds);
            // 그 productTag에서 또 productId를 가져옴.
            List<Long> productIds = productTags.stream()
                    .map(productTag -> productTag.getProduct().getId())
                    .toList();

            List<Product> productList = productRepository.findByIdIn(productIds);

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

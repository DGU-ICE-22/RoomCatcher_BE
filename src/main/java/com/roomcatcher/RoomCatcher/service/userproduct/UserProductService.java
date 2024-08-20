package com.roomcatcher.RoomCatcher.service.userproduct;

import com.roomcatcher.RoomCatcher.domain.Product;
import com.roomcatcher.RoomCatcher.domain.User;
import com.roomcatcher.RoomCatcher.domain.UserProduct;
import com.roomcatcher.RoomCatcher.dto.userproduct.request.CreateUserProductDTO;
import com.roomcatcher.RoomCatcher.repository.ProductRepository;
import com.roomcatcher.RoomCatcher.repository.UserProductRepository;
import com.roomcatcher.RoomCatcher.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserProductService {
    private final UserProductRepository userProductRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    // 찜한 매물로 등록
    @Transactional
    public void createUserProduct(CreateUserProductDTO createUserProductDTO){
        // 찜한 매물의 productId를 가져옴.
        // 사용자의 userId를 가져옴.
        // 찜한 매물을 생성함.
        // 찜한 매물을 저장함.
        Product product = productRepository.findById(createUserProductDTO.getProductId())
                .orElseThrow(IllegalArgumentException::new);
        User user = userRepository.findById(createUserProductDTO.getUserId())
                .orElseThrow(IllegalArgumentException::new);
        UserProduct userProduct = createUserProductDTO.toProduct(user, product);
        userProductRepository.save(userProduct);
        log.info("찜한 매물 저장 완료");
    }

    // 찜한 매물에서 삭제
    @Transactional
    public void deleteUserProduct(Long userId, Long productId){

        // 해당하는 UserProduct를 삭제하기
        // 성공 여부 반환
        UserProduct userProduct = userProductRepository.findByUserIdAndProductId(userId, productId)
                .orElseThrow(IllegalArgumentException::new);
        userProductRepository.delete(userProduct);
        log.info("찜한 매물 삭제 완료");
    }
}

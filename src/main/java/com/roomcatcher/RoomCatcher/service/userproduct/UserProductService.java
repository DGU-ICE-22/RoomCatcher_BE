package com.roomcatcher.RoomCatcher.service.userproduct;

import ch.qos.logback.classic.Logger;
import com.roomcatcher.RoomCatcher.domain.Product;
import com.roomcatcher.RoomCatcher.domain.User;
import com.roomcatcher.RoomCatcher.domain.UserProduct;
import com.roomcatcher.RoomCatcher.dto.userproduct.request.CreateUserProductDTO;
import com.roomcatcher.RoomCatcher.dto.userproduct.request.DeleteUserProductDTO;
import com.roomcatcher.RoomCatcher.global.exception.BusinessException;
import com.roomcatcher.RoomCatcher.global.exception.message.ErrorMessage;
import com.roomcatcher.RoomCatcher.global.jwt.JwtTokenProvider;
import com.roomcatcher.RoomCatcher.repository.ProductRepository;
import com.roomcatcher.RoomCatcher.repository.UserProductRepository;
import com.roomcatcher.RoomCatcher.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProductService {
    private final UserProductRepository userProductRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    // 찜한 매물로 등록
    @Transactional
    public void createUserProduct(String token, Long productId) {
        // 찜한 매물의 productId를 가져옴.
        // 사용자의 userId를 가져옴.
        // 찜한 매물을 생성함.
        // 찜한 매물을 저장함.
        try {
            Product product = productRepository.findById(productId).orElseThrow(IllegalArgumentException::new);
            User user = userRepository.findById(jwtTokenProvider.getUserFromJwt(token)).orElseThrow(IllegalArgumentException::new);
            UserProduct userProduct = new UserProduct(user, product);
            userProductRepository.save(userProduct);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    // 찜한 매물에서 삭제
    @Transactional
    public void deleteUserProduct(String token, Long productId) {

        // 해당하는 UserProduct를 삭제하기
        // 성공 여부 반환
        UserProduct userProduct = userProductRepository.findByUserIdAndProductId(jwtTokenProvider.getUserFromJwt(token), productId)
                .orElseThrow(IllegalArgumentException::new);
        userProductRepository.delete(userProduct);
    }
}

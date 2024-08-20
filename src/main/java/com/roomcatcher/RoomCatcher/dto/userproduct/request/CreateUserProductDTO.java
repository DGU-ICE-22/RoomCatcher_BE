package com.roomcatcher.RoomCatcher.dto.userproduct.request;

import com.roomcatcher.RoomCatcher.domain.Product;
import com.roomcatcher.RoomCatcher.domain.User;
import com.roomcatcher.RoomCatcher.domain.UserProduct;
import lombok.Data;
import lombok.Getter;

// 맞춤 매물로 등록하기
@Getter
@Data
public class CreateUserProductDTO {
    private Long productId;
    private Long userId;

    public UserProduct toProduct(User user, Product product){
        return new UserProduct(user, product);
    }

}

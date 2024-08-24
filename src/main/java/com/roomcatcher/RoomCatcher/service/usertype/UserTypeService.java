package com.roomcatcher.RoomCatcher.service.usertype;

import com.roomcatcher.RoomCatcher.domain.Type;
import com.roomcatcher.RoomCatcher.domain.User;
import com.roomcatcher.RoomCatcher.domain.UserType;
import com.roomcatcher.RoomCatcher.dto.usertype.request.CreateUserTypeDTO;
import com.roomcatcher.RoomCatcher.dto.usertype.response.UserTypeResponse;
import com.roomcatcher.RoomCatcher.global.exception.BusinessException;
import com.roomcatcher.RoomCatcher.global.exception.message.ErrorMessage;
import com.roomcatcher.RoomCatcher.global.jwt.JwtTokenProvider;
import com.roomcatcher.RoomCatcher.repository.TypeRepository;
import com.roomcatcher.RoomCatcher.repository.UserRepository;
import com.roomcatcher.RoomCatcher.repository.UserTypeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserTypeService {
    private final UserTypeRepository userTypeRepository;
    private final UserRepository userRepository;
    private final TypeRepository typeRepository;
    private final JwtTokenProvider jwtTokenProvider;

    // 사용자의 유형을 생성함.
    @Transactional
    public UserTypeResponse createUserType(String token, CreateUserTypeDTO createUserTypeDTO){
       try {
        Long userId = jwtTokenProvider.getUserFromJwt(token);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(ErrorMessage.NOT_FOUND_USER));
        String typeName = createUserTypeDTO.getTypeName();
        Type type = typeRepository.findByTypeName(typeName)
                .orElseThrow(() -> new BusinessException(ErrorMessage.NOT_FOUND_TYPE));

//        List<String> tags = createUserTypeDTO.getTags();

        UserType userType = UserType.builder()
                .users(user)
                .type(type)
                .build();
        return UserTypeResponse.of(userTypeRepository.save(userType).getId());
    } catch (Exception e) {
        log.error(e.getMessage());
        throw new BusinessException(ErrorMessage.CREATE_USER_TYPE_FAIL);
    }
    }
}


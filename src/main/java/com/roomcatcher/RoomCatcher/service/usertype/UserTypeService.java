package com.roomcatcher.RoomCatcher.service.usertype;

import com.roomcatcher.RoomCatcher.domain.*;
import com.roomcatcher.RoomCatcher.dto.usertype.request.CreateUserTypeDTO;
import com.roomcatcher.RoomCatcher.dto.usertype.response.UserTypeResponse;
import com.roomcatcher.RoomCatcher.global.exception.BusinessException;
import com.roomcatcher.RoomCatcher.global.exception.message.ErrorMessage;
import com.roomcatcher.RoomCatcher.global.jwt.JwtTokenProvider;
import com.roomcatcher.RoomCatcher.repository.*;
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
    private final UserTagRepository userTagRepository;
    private final TagRepository tagRepository;

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
            List<String> tags = createUserTypeDTO.getTags();

            // 기존 UserType 데이터가 있는지 확인
            UserType existingUserType = userTypeRepository.findByUsersId(user.getId());

            if (existingUserType != null) {
                // 기존 데이터 삭제
                userTypeRepository.delete(existingUserType);
            }
            //tags들을 user_tag 테이블에 userId와 tagId로 저장함.
            for (String tagName : tags) {
                Tag tag = tagRepository.findByTagName(tagName)
                        .orElseThrow(() -> new BusinessException(ErrorMessage.NOT_FOUND_TAG));
                UserTag userTag = new UserTag(user, tag);
                userTagRepository.save(userTag);
            }
            //userType 테이블에 user와 type 객체로 저장함.
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


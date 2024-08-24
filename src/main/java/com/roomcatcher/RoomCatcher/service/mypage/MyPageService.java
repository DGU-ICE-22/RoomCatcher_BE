package com.roomcatcher.RoomCatcher.service.mypage;

import com.roomcatcher.RoomCatcher.domain.Tag;
import com.roomcatcher.RoomCatcher.domain.User;
import com.roomcatcher.RoomCatcher.domain.UserTag;
import com.roomcatcher.RoomCatcher.dto.mypage.request.UserTagRequestDto;
import com.roomcatcher.RoomCatcher.dto.mypage.response.GetTagResponseDto;
import com.roomcatcher.RoomCatcher.dto.mypage.response.MypageReponseDto;
import com.roomcatcher.RoomCatcher.dto.mypage.response.UserTagResponseDto;
import com.roomcatcher.RoomCatcher.global.jwt.JwtTokenProvider;
import com.roomcatcher.RoomCatcher.repository.TagRepository;
import com.roomcatcher.RoomCatcher.repository.UserRepository;
import com.roomcatcher.RoomCatcher.repository.UserTagRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.Collator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MyPageService {

    private final UserTagRepository userTagRepository;
    private final TagRepository tagRepository;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    // 해시태그 목록 조회
    public GetTagResponseDto getTags() {
        List<String> tagNames = tagRepository.findAll().stream()
                                    .map(tag -> tag.getTagName())
                                    .sorted(this::compareTags)
                                    .collect(Collectors.toList());

        return GetTagResponseDto.of(tagNames);
    }

    // 태그들 순서 비교
    private int compareTags(String tag1, String tag2) {
        int priorityComparison = Integer.compare(getPriority(tag1), getPriority(tag2));

        // 우선순위에 따라 비교 (특수문자, 숫자, 영문자, 한글 순)
        if (priorityComparison != 0) {
            return priorityComparison;
        }
        // 만약, 한글인경우 오름차순
        if (getPriority(tag1) == 4) {
            return getCollator().compare(tag1, tag2);
        }
        // 숫자, 영문자, 특수기호는 기본 문자열 정렬
        return tag1.compareTo(tag2);
    }

    private int getPriority(String tag) {
        char firstChar = tag.charAt(0);
        if (isSpecialCharacter(firstChar)) return 1; // 특수 문자 : 1
        if (Character.isDigit(firstChar)) return 2; // 숫자 : 2
        if (isEnglish(firstChar)) return 3; // 영문자 : 3
        return 4; // 한글 : 4
    }

    private boolean isSpecialCharacter(char c) {
        return !Character.isLetterOrDigit(c);
    }

    private boolean isEnglish(char c) {
        return Character.isLetter(c) && c <= 'z';
    }

    // 한글 정렬
    private Collator getCollator() {
        Collator collator = Collator.getInstance(Locale.KOREAN);
        collator.setStrength(Collator.PRIMARY);
        return collator;
    }

    // 사용자의 선호정보 업데이트
    @Transactional
    public UserTagResponseDto updateUserTags(String token, UserTagRequestDto requestDto) {
        // 사용자의 기존 태그 삭제
        userTagRepository.deleteByUser(userRepository.findByToken(token, jwtTokenProvider));

        // 새로운 태그 저장
        List<Tag> tags = tagRepository.findByTagNameIn(requestDto.getTagNames());

        List<UserTag> userTags = tags.stream()
                                     .map(tag -> new UserTag(userRepository.findByToken(token, jwtTokenProvider), tag))
                                     .collect(Collectors.toList());

        userTagRepository.saveAll(userTags);

        // 태그 목록 반환
        List<String> UserTags = userTags.stream()
                                        .map(userTag -> userTag.getTag().getTagName())
                                        .collect(Collectors.toList());

        return new UserTagResponseDto(UserTags);
    }

    // 마이페이지 조회
    public MypageReponseDto getMyPage(String token) {
        User user = userRepository.findByToken(token, jwtTokenProvider);

        // 생년월일 변환 (예시) 030423 -> 03.04.23
        String Birth = user.getUserBirth().substring(0, 2) + "." + user.getUserBirth().substring(2, 4) + "." + user.getUserBirth().substring(4);

        // 선호 태그 조회
        List<UserTag> userTags = userTagRepository.findByUser(userRepository.findByToken(token, jwtTokenProvider));
        List<String> tagNames = userTags.stream()
                                    .map(userTag -> userTag.getTag().getTagName())
                                    .sorted(this::compareTags)
                                    .collect(Collectors.toList());

        return MypageReponseDto.of(
            user.getUserName(),
            Birth,
            user.getUserSex(),
            user.getUserlocation(),
            tagNames
        );
    }
}


package com.roomcatcher.RoomCatcher.service.mypage;

import com.roomcatcher.RoomCatcher.dto.mypage.GetTagResponseDto;
import com.roomcatcher.RoomCatcher.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.Collator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MyPageService {

    private final TagRepository tagRepository;
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
}


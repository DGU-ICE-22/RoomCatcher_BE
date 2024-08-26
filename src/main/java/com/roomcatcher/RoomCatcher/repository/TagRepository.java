package com.roomcatcher.RoomCatcher.repository;

import com.roomcatcher.RoomCatcher.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {

    List<Tag> findAll();
    List<Tag> findByTagNameIn(List<String> tagNames); // 특정 태그 이름으로 태그 조회

    Optional<Tag> findByTagName(String tagName);
}

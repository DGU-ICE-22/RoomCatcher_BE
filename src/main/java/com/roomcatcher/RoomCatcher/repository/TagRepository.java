package com.roomcatcher.RoomCatcher.repository;

import com.roomcatcher.RoomCatcher.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {
    List<Tag> findAll();
    List<Tag> findByTagNameIn(List<String> tagNames); // 특정 태그 이름으로 태그 조회
}
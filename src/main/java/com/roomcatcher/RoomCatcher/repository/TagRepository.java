package com.roomcatcher.RoomCatcher.repository;

import com.roomcatcher.RoomCatcher.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {
    List<Tag> findAll(); // 태그 전체 목록을 조회
}ㄹ
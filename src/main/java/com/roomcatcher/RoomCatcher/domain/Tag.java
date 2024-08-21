package com.roomcatcher.RoomCatcher.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "dataAnalyze_tag_detail", schema = "RoomCatcherDB")
public class Tag {

    @Id
    @Column(name = "tagId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String tagName;

    @Lob
    private byte[] embedding;

    @OneToMany(mappedBy = "tag")
    private List<UserTag> userTags = new ArrayList<>();

    @OneToMany(mappedBy = "tag")
    private List<ProductTag> productTags = new ArrayList<>();

}

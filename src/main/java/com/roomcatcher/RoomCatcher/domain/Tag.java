package com.roomcatcher.RoomCatcher.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "`data_analyze_tag_detail`", schema = "RoomCatcherDB")
public class Tag {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String tagName;

    @Lob
    private Blob embedding;

    @OneToMany(mappedBy = "tag")
    private List<UserTag> userTags = new ArrayList<>();

    @OneToMany(mappedBy = "tag")
    private List<ProductTag> productTags = new ArrayList<>();

}

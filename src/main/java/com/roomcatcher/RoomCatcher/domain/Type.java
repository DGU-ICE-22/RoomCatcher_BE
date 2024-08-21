package com.roomcatcher.RoomCatcher.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "report_and_recommand_user_type", schema = "RoomCatcherDB")
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "typeId", nullable = false)
    private Integer Id;

    @Column(nullable = false, length = 64)
    private String typeName;

    @Column(nullable = false, length = 1024)
    private String typeExplain;

    @Column(name = "embedding")
    @Lob
    private byte[] embedding;

    @Column(name = "typeImage")
    @Transient
    private String typeImage;

    @OneToMany(mappedBy = "type")
    private List<UserType> userTypes = new ArrayList<>();

}
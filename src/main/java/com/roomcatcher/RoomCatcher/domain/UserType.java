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
public class UserType {

    @Id
    @Column(name = "userTypeId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false)
    private String typeName;

    private String typeComment;

    @Column(nullable = false)
    private String typeImage;

    @OneToMany(mappedBy = "userType")
    private List<User> users = new ArrayList<>();

    @OneToMany(mappedBy = "userType")
    private List<UserTypeTag> userTypeTags = new ArrayList<>();
}

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
public class User {

    @Id
    @Column(name = "userId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String userSex;

    @Column(nullable = false)
    private String userLocation;

    @Column(nullable = false)
    private Integer userAge;

    @Column(nullable = false)
    private String userImage;

    @OneToMany(mappedBy = "user")
    private List<UserProduct> userProducts = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<UserTag> userTags = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userTypeId")
    private UserType userType;
}

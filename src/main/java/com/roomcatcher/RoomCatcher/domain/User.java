package com.roomcatcher.RoomCatcher.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
public class User {

    @Id
    @Column(name = "userId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String userBirth;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String userSex;

    @Column
    private String userImage;

    @Column
    private String userlocation;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserProduct> userProducts = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserTag> userTags = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userTypeId")
    private UserType userType;

    @Builder
    public User(String userName, String userBirth, String email, String password, String userSex, String userImage, UserType userType, String userlocation) {
        this.userName = userName;
        this.userBirth = userBirth;
        this.email = email;
        this.password = password;
        this.userSex = userSex;
        this.userImage = userImage;
        this.userType = userType;
        this.userlocation = userlocation;
    }

    public static User of(String userName, String userBirth, String email, String password, String userSex, String userImage, UserType userType, String userLocation) {
        return new User(userName, userBirth, email, password, userSex, userImage, userType, userLocation);
    }

    public void updateUserInfo(String birth, String sex, String residence) {
        this.userBirth = birth;
        this.userSex = sex;
        this.userlocation = residence;
    }
}

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
public class Product {

    @Id
    @Column(name = "productId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private String productRoomType;

    @Column(nullable = false)
    private String productSellingType;

    @Column(nullable = false)
    private String productPrice;

    @Column(nullable = false)
    private String productAddr;

    private String productInfo;

    @Column(nullable = false)
    private String productImage;

    @Column(nullable = false)
    private Boolean productIsContract;

    @Column(nullable = false)
    private Boolean productIsQuick;

    @OneToMany(mappedBy = "product")
    private List<UserProduct> userProducts = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<ProductTag> productTags = new ArrayList<>();
}
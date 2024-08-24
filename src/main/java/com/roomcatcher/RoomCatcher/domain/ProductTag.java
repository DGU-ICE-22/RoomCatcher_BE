package com.roomcatcher.RoomCatcher.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigInteger;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "`data_analyze_product_tag_detail`", schema = "RoomCatcherDB")
public class ProductTag {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "product_detail_id", referencedColumnName = "id")
    private Product product;

    @Column(name = "listing_serial_number")
    private Long listingSerialNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "tag_id", referencedColumnName = "id")
    private Tag tag;
}

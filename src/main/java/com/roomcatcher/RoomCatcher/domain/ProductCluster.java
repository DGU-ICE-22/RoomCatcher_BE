package com.roomcatcher.RoomCatcher.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "`data_analyze_product_kb`", schema = "RoomCatcherDB")
public class ProductCluster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Lob
    @Column(name = "층구분")
    private String 층구분;

    @Column(name = "연면적")
    private Double 연면적;

    @Column(name = "욕실수")
    private Integer 욕실수;

    @Column(name = "전세가율")
    private Double 전세가율;

    @Lob
    @Column(name = "주택형")
    private String 주택형;

    @Column(name = "최소월세가")
    private Integer 최소월세가;

    @Lob
    @Column(name = "매물유입구분")
    private String 매물유입구분;

    @Column(name = "매매가")
    private Integer 매매가;

    @Column(name = "위도")
    private Double 위도;

    @Column(name = "건축면적")
    private Double 건축면적;

    @Lob
    @Column(name = "허위매물처리결과")
    private String 허위매물처리결과;

    @Lob
    @Column(name = "건축물용도")
    private String 건축물용도;

    @Lob
    @Column(name = "단지명")
    private String 단지명;

    @Column(name = "월세보증금")
    private Integer 월세보증금;

    @Column(name = "방수")
    private Integer 방수;

    @Lob
    @Column(name = "사용월")
    private String 사용월;

    @Column(name = "전용면적")
    private Double 전용면적;

    @Lob
    @Column(name = "중개업소주소")
    private String 중개업소주소;

    @Lob
    @Column(name = "등록일")
    private String 등록일;

    @Lob
    @Column(name = "매물상태")
    private String 매물상태;

    @Column(name = "사용년차")
    private Double 사용년차;

    @Lob
    @Column(name = "건폐율")
    private String 건폐율;

    @Column(name = "매물일련번호")
    private Integer 매물일련번호;

    @Lob
    @Column(name = "광고내용")
    private String 광고내용;

    @Column(name = "이미지수")
    private Integer 이미지수;

    @Column(name = "순공급면적")
    private Double 순공급면적;

    @Lob
    @Column(name = "매물종별")
    private String 매물종별;

    @Column(name = "순전용면적")
    private Double 순전용면적;

    @Lob
    @Column(name = "매물유입구분명")
    private String 매물유입구분명;

    @Column(name = "최대전세가")
    private Integer 최대전세가;

    @Lob
    @Column(name = "매물거래구분")
    private String 매물거래구분;

    @Lob
    @Column(name = "용적률")
    private String 용적률;

    @Lob
    @Column(name = "융자금")
    private String 융자금;

    @Lob
    @Column(name = "중개업소명")
    private String 중개업소명;

    @Column(name = "최대대출가능금액")
    private Double 최대대출가능금액;

    @Column(name = "총층수")
    private Integer 총층수;

    @Lob
    @Column(name = "매물명")
    private String 매물명;

    @Column(name = "단지기본일련번호")
    private Integer 단지기본일련번호;

    @Column(name = "공급면적")
    private Double 공급면적;

    @Lob
    @Column(name = "입주가능일")
    private String 입주가능일;

    @Column(name = "실거래가대비")
    private Integer 실거래가대비;

    @Lob
    @Column(name = "층수")
    private String 층수;

    @Column(name = "경도")
    private Double 경도;

    @Lob
    @Column(name = "사용년")
    private String 사용년;

    @Lob
    @Column(name = "클러스터식별자")
    private String 클러스터식별자;

    @Lob
    @Column(name = "시군구주소")
    private String 시군구주소;

    @Column(name = "평당단가")
    private Integer 평당단가;

    @Lob
    @Column(name = "방향구분")
    private String 방향구분;

    @Column(name = "승강기유무")
    private Integer 승강기유무;

    @Lob
    @Column(name = "매물종별구분")
    private String 매물종별구분;

    @Lob
    @Column(name = "카테고리2")
    private String 카테고리2;

    @Lob
    @Column(name = "매물종별그룹구분")
    private String 매물종별그룹구분;

    @Lob
    @Column(name = "상세번지")
    private String 상세번지;

    @Lob
    @Column(name = "건물명")
    private String 건물명;

    @Column(name = "대지면적")
    private Double 대지면적;

    @Lob
    @Column(name = "이미지URL")
    private String 이미지url;

    @Lob
    @Column(name = "현관구조")
    private String 현관구조;

    @Lob
    @Column(name = "주소")
    private String 주소;

}
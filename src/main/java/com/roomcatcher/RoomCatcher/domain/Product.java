package com.roomcatcher.RoomCatcher.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "dataAnalyze_ProductKB_detail", schema = "RoomCatcherDB")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "매물일련번호")
    private Long 매물일련번호;

    @Column(name = "단지기본일련번호")
    private Long 단지기본일련번호;

    @Lob
    @Column(name = "매물명")
    private String 매물명;

    @Lob
    @Column(name = "매물도로기본주소")
    private String 매물도로기본주소;

    @Lob
    @Column(name = "매물도로상세주소")
    private String 매물도로상세주소;

    @Column(name = "해당층수")
    private Integer 해당층수;

    @Lob
    @Column(name = "카테고리1")
    private String 카테고리1;

    @Lob
    @Column(name = "카테고리2")
    private String 카테고리2;

    @Column(name = "지하구분")
    private String 지하구분;

    @Column(name = "지하층수")
    private String 지하층수;

    @Column(name = "총지상층수")
    private Integer 총지상층수;

    @Column(name = "방향명")
    private String 방향명;

    @Column(name = "공급면적")
    private Double 공급면적;

    @Column(name = "전용면적")
    private Double 전용면적;

    @Column(name = "연면적")
    private Double 연면적;

    @Column(name = "대지면적")
    private Double 대지면적;

    @Column(name = "건축면적")
    private Double 건축면적;

    @Column(name = "공급면적평")
    private Double 공급면적평;

    @Column(name = "전용면적평")
    private Double 전용면적평;

    @Column(name = "방수")
    private Integer 방수;

    @Column(name = "욕실수")
    private Integer 욕실수;

    @Column(name = "현관구조내용")
    private String 현관구조내용;

    @Lob
    @Column(name = "입주가능일내용")
    private String 입주가능일내용;

    @Column(name = "입주가능일협의여부")
    private String 입주가능일협의여부;

    @Column(name = "매매가")
    private Integer 매매가;

    @Column(name = "전세가")
    private Integer 전세가;

    @Column(name = "월세가")
    private Integer 월세가;

    @Column(name = "월세보증금")
    private Integer 월세보증금;

    @Column(name = "융자금액")
    private Integer 융자금액;

    @Column(name = "보증금총액")
    private Integer 보증금총액;

    @Column(name = "월세금총액")
    private Integer 월세금총액;

    @Column(name = "기전세금액")
    private Integer 기전세금액;

    @Column(name = "기월세액")
    private Integer 기월세액;

    @Column(name = "전세전환시금액")
    private Integer 전세전환시금액;

    @Column(name = "옵션금액")
    private Integer 옵션금액;

    @Column(name = "분양가")
    private Integer 분양가;

    @Column(name = "권리금액")
    private Integer 권리금액;

    @Column(name = "채권금액")
    private Integer 채권금액;

    @Lob
    @Column(name = "특징광고내용")
    private String 특징광고내용;

    @Lob
    @Column(name = "물건특징내용")
    private String 물건특징내용;

    @Lob
    @Column(name = "제휴중개업소명")
    private String 제휴중개업소명;

    @Lob
    @Column(name = "매물주차가능여부")
    private String 매물주차가능여부;

    @Column(name = "매물주차대수")
    private Integer 매물주차대수;

    @Column(name = "세대당주차대수비율")
    private Double 세대당주차대수비율;

    @Lob
    @Column(name = "난방방식명")
    private String 난방방식명;

    @Lob
    @Column(name = "내진설계여부")
    private String 내진설계여부;

    @Lob
    @Column(name = "방거실형태명")
    private String 방거실형태명;

    @Lob
    @Column(name = "원룸구조명")
    private String 원룸구조명;

    @Column(name = "관리비")
    private Integer 관리비;

    @Column(name = "관리비_전기세여부")
    private Integer 관리비전기세여부;

    @Column(name = "관리비_가스여부")
    private Integer 관리비가스여부;

    @Column(name = "관리비_수도여부")
    private Integer 관리비수도여부;

    @Column(name = "관리비_인터넷여부")
    private Integer 관리비인터넷여부;

    @Column(name = "관리비_tv여부")
    private Integer 관리비Tv여부;

    @Column(name = "시스템에어컨여부")
    private Integer 시스템에어컨여부;

    @Column(name = "벽걸이에어컨여부")
    private Integer 벽걸이에어컨여부;

    @Column(name = "입식에어컨여부")
    private Integer 입식에어컨여부;

    @Column(name = "침대여부")
    private Integer 침대여부;

    @Column(name = "책상여부")
    private Integer 책상여부;

    @Column(name = "옷장여부")
    private Integer 옷장여부;

    @Column(name = "붙박이장여부")
    private Integer 붙박이장여부;

    @Column(name = "식탁여부")
    private Integer 식탁여부;

    @Column(name = "소파여부")
    private Integer 소파여부;

    @Column(name = "신발장여부")
    private Integer 신발장여부;

    @Column(name = "냉장고여부")
    private Integer 냉장고여부;

    @Column(name = "세탁기여부")
    private Integer 세탁기여부;

    @Column(name = "건조기여부")
    private Integer 건조기여부;

    @Column(name = "샤워부스여부")
    private Integer 샤워부스여부;

    @Column(name = "욕조여부")
    private Integer 욕조여부;

    @Column(name = "비데여부")
    private Integer 비데여부;

    @Column(name = "싱크대여부")
    private Integer 싱크대여부;

    @Column(name = "식기세척기여부")
    private Integer 식기세척기여부;

    @Column(name = "가스레인지여부")
    private Integer 가스레인지여부;

    @Column(name = "인덕션레인지여부")
    private Integer 인덕션레인지여부;

    @Column(name = "베란다여부")
    private Integer 베란다여부;

    @Column(name = "자체경비원여부")
    private Integer 자체경비원여부;

    @Column(name = "비디오전화여부")
    private Integer 비디오전화여부;

    @Column(name = "인터폰여부")
    private Integer 인터폰여부;

    @Column(name = "cctv여부")
    private Integer cctv여부;

    @Column(name = "방범창여부")
    private Integer 방범창여부;

    @Column(name = "현관보안여부")
    private Integer 현관보안여부;

    @Column(name = "무인택배박스여부")
    private Integer 무인택배박스여부;

    @Column(name = "엘리베이터여부")
    private Integer 엘리베이터여부;

    @Column(name = "테라스여부")
    private Integer 테라스여부;

    @Column(name = "마당여부")
    private Integer 마당여부;

    @Lob
    @Column(name = "사용승인일")
    private String 사용승인일;

    @Lob
    @Column(name = "수정일시")
    private String 수정일시;

    @OneToMany(mappedBy = "product")
    private List<ProductTag> productTags = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<UserProduct> userProducts = new ArrayList<>();
}
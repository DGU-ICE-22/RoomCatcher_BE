package com.roomcatcher.RoomCatcher.dto.product.response;

import com.roomcatcher.RoomCatcher.domain.Product;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
public record ProductResponseDTO(
        Long id,
        Long 매물일련번호,
        Long 단지기본일련번호,
        String 매물명,
        String 매물도로기본주소,
        String 매물도로상세주소,
        Integer 해당층수,
        String 카테고리1,
        String 카테고리2,
        String 지하구분,
        String 지하층수,
        Integer 총지상층수,
        String 방향명,
        Double 공급면적,
        Double 전용면적,
        Double 연면적,
        Double 대지면적,
        Double 건축면적,
        Double 공급면적평,
        Double 전용면적평,
        Integer 방수,
        Integer 욕실수,
        String 현관구조내용,
        String 입주가능일내용,
        String 입주가능일협의여부,
        Integer 매매가,
        Integer 전세가,
        Integer 월세가,
        Integer 월세보증금,
        Integer 융자금액,
        Integer 보증금총액,
        Integer 월세금총액,
        Integer 기전세금액,
        Integer 기월세액,
        Integer 전세전환시금액,
        Integer 옵션금액,
        Integer 분양가,
        Integer 권리금액,
        Integer 채권금액,
        String 특징광고내용,
        String 물건특징내용,
        String 제휴중개업소명,
        String 매물주차가능여부,
        Integer 매물주차대수,
        Double 세대당주차대수비율,
        String 난방방식명,
        String 내진설계여부,
        String 방거실형태명,
        String 원룸구조명,
        Integer 관리비,
        Integer 관리비전기세여부,
        Integer 관리비가스여부,
        Integer 관리비수도여부,
        Integer 관리비인터넷여부,
        Integer 관리비Tv여부,
        Integer 시스템에어컨여부,
        Integer 벽걸이에어컨여부,
        Integer 입식에어컨여부,
        Integer 침대여부,
        Integer 책상여부,
        Integer 옷장여부,
        Integer 붙박이장여부,
        Integer 식탁여부,
        Integer 소파여부,
        Integer 신발장여부,
        Integer 냉장고여부,
        Integer 세탁기여부,
        Integer 건조기여부,
        Integer 샤워부스여부,
        Integer 욕조여부,
        Integer 비데여부,
        Integer 싱크대여부,
        Integer 식기세척기여부,
        Integer 가스레인지여부,
        Integer 인덕션레인지여부,
        Integer 베란다여부,
        Integer 자체경비원여부,
        Integer 비디오전화여부,
        Integer 인터폰여부,
        Integer cctv여부,
        Integer 방범창여부,
        Integer 현관보안여부,
        Integer 무인택배박스여부,
        Integer 엘리베이터여부,
        Integer 테라스여부,
        Integer 마당여부,
        String 사용승인일,
        String 수정일시
) {
    public static ProductResponseDTO of(
            final Product product
    ) {
        return ProductResponseDTO.builder()
                .id(product.getId())
                .매물일련번호(product.get매물일련번호())
                .단지기본일련번호(product.get단지기본일련번호())
                .매물명(product.get매물명())
                .매물도로기본주소(product.get매물도로기본주소())
                .매물도로상세주소(product.get매물도로상세주소())
                .해당층수(product.get해당층수())
                .카테고리1(product.get카테고리1())
                .카테고리2(product.get카테고리2())
                .지하구분(product.get지하구분())
                .지하층수(product.get지하층수())
                .총지상층수(product.get총지상층수())
                .방향명(product.get방향명())
                .공급면적(product.get공급면적())
                .전용면적(product.get전용면적())
                .연면적(product.get연면적())
                .대지면적(product.get대지면적())
                .건축면적(product.get건축면적())
                .공급면적평(product.get공급면적평())
                .전용면적평(product.get전용면적평())
                .방수(product.get방수())
                .욕실수(product.get욕실수())
                .현관구조내용(product.get현관구조내용())
                .입주가능일내용(product.get입주가능일내용())
                .입주가능일협의여부(product.get입주가능일협의여부())
                .매매가(product.get매매가())
                .전세가(product.get전세가())
                .월세가(product.get월세가())
                .월세보증금(product.get월세보증금())
                .융자금액(product.get융자금액())
                .보증금총액(product.get보증금총액())
                .월세금총액(product.get월세금총액())
                .기전세금액(product.get기전세금액())
                .기월세액(product.get기월세액())
                .전세전환시금액(product.get전세전환시금액())
                .옵션금액(product.get옵션금액())
                .분양가(product.get분양가())
                .권리금액(product.get권리금액())
                .채권금액(product.get채권금액())
                .특징광고내용(product.get특징광고내용())
                .물건특징내용(product.get물건특징내용())
                .제휴중개업소명(product.get제휴중개업소명())
                .매물주차가능여부(product.get매물주차가능여부())
                .매물주차대수(product.get매물주차대수())
                .세대당주차대수비율(product.get세대당주차대수비율())
                .난방방식명(product.get난방방식명())
                .내진설계여부(product.get내진설계여부())
                .방거실형태명(product.get방거실형태명())
                .원룸구조명(product.get원룸구조명())
                .관리비(product.get관리비())
                .관리비전기세여부(product.get관리비전기세여부())
                .관리비가스여부(product.get관리비가스여부())
                .관리비수도여부(product.get관리비수도여부())
                .관리비인터넷여부(product.get관리비인터넷여부())
                .관리비Tv여부(product.get관리비Tv여부())
                .시스템에어컨여부(product.get시스템에어컨여부())
                .벽걸이에어컨여부(product.get벽걸이에어컨여부())
                .입식에어컨여부(product.get입식에어컨여부())
                .침대여부(product.get침대여부())
                .책상여부(product.get책상여부())
                .옷장여부(product.get옷장여부())
                .붙박이장여부(product.get붙박이장여부())
                .식탁여부(product.get식탁여부())
                .소파여부(product.get소파여부())
                .신발장여부(product.get신발장여부())
                .냉장고여부(product.get냉장고여부())
                .세탁기여부(product.get세탁기여부())
                .건조기여부(product.get건조기여부())
                .샤워부스여부(product.get샤워부스여부())
                .욕조여부(product.get욕조여부())
                .비데여부(product.get비데여부())
                .싱크대여부(product.get싱크대여부())
                .식기세척기여부(product.get식기세척기여부())
                .가스레인지여부(product.get가스레인지여부())
                .인덕션레인지여부(product.get인덕션레인지여부())
                .베란다여부(product.get베란다여부())
                .자체경비원여부(product.get자체경비원여부())
                .비디오전화여부(product.get비디오전화여부())
                .인터폰여부(product.get인터폰여부())
                .cctv여부(product.getCctv여부())
                .방범창여부(product.get방범창여부())
                .현관보안여부(product.get현관보안여부())
                .무인택배박스여부(product.get무인택배박스여부())
                .엘리베이터여부(product.get엘리베이터여부())
                .테라스여부(product.get테라스여부())
                .마당여부(product.get마당여부())
                .사용승인일(product.get사용승인일())
                .수정일시(product.get수정일시())
                .build();

    }
}
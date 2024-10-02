package edu.sm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date; // Date 클래스 임포트

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cart {
    private Integer sbagId; // 장바구니 ID
    private Date createAt; // 생성 날짜 (Date 타입으로 변경)
    private Integer quantity; // 수량
    private Integer productId; // 제품 ID
    private Integer userId; // 사용자 ID

}

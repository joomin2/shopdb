package edu.sm.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cart {
    private Integer sbagId; // 장바구니 ID
    private Date createAt; // 생성 날짜 (Date 타입으로 변경)
    private Integer quantity; // 수량
    private Integer productId; // 제품 ID
    private String userId; // 사용자 ID

}

package edu.sm.dto;

import lombok.*; // Lombok 임포트

import java.sql.Date; // SQL Date 클래스 임포트

@Data // Getter, Setter, toString 등 자동 생성
@AllArgsConstructor // 모든 필드 생성자
@NoArgsConstructor // 기본 생성자
@Builder // 빌더 패턴 사용
public class Shipping {
    private int shippingId; // 배송 ID
    private String shippingAdress; // 배송 주소
    private Date shippingDate; // 배송 날짜
    private String shippingStatus; // 배송 상태
    private int orderId; // 주문 ID
}
